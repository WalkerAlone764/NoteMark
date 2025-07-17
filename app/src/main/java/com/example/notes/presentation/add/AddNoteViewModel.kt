package com.example.notes.presentation.add

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.core.presentation.util.asUiText
import com.example.core.util.Result
import com.example.notemark.navigation.Routes
import com.example.notes.domain.model.Note
import com.example.notes.domain.repository.NoteRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.ZonedDateTime

class AddNoteViewModel(
    savedStateHandle: SavedStateHandle, private val noteRepository: NoteRepository
) : ViewModel() {

    val screenRoute = savedStateHandle.toRoute<Routes.AddNoteScreen>()

    private var hasLoadedInitialData = false
    private lateinit var originalNote: Note

    private val _state = MutableStateFlow(AddNoteState())
    val state = _state.onStart {
            if (!hasLoadedInitialData) {
                initialize()
                hasLoadedInitialData = true
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = AddNoteState()
        )

    private val _event = Channel<AddNoteEvent>()
    val event = _event.receiveAsFlow()

    fun onAction(action: AddNoteAction) {
        when (action) {
            is AddNoteAction.OnChangeContent -> onChangeContent(action.content)
            is AddNoteAction.OnChangeTitle -> onChangeTitle(action.title)
            AddNoteAction.OnClickSaveNote -> onClickSaveNote()
        }
    }

    private fun onClickSaveNote() {
        viewModelScope.launch {
            val note = originalNote.copy(
                title = state.value.title,
                content = state.value.content,
                lastEditedAt = ZonedDateTime.now()
            )
            val result = noteRepository.updateNote(note)
            when (result) {
                is Result.Error -> {
                    _event.send(AddNoteEvent.OnError(result.error.asUiText()))

                }

                is Result.Success -> {
                    _event.send(AddNoteEvent.SuccessfullyUpdated)

                }
            }
        }
    }

    private fun onChangeTitle(title: String) {
        _state.update { it.copy(title = title) }
    }

    private fun onChangeContent(content: String) {
        _state.update { it.copy(content = content) }
    }

    fun initialize() {
        viewModelScope.launch {
            val result = noteRepository.getNoteById(screenRoute.noteId)
            result?.let { note ->
                originalNote = note
                _state.update { state ->
                    state.copy(
                        title = note.title, content = note.content
                    )
                }
            }
        }
    }

}