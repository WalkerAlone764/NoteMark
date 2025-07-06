package com.example.notes.presentation.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.SessionStorage
import com.example.core.presentation.util.asUiText
import com.example.core.util.Result
import com.example.notes.domain.model.Note
import com.example.notes.domain.repository.NoteRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.ZonedDateTime
import java.util.UUID

class NoteListViewModel(
    private val sessionStorage: SessionStorage, private val noteRepository: NoteRepository
) : ViewModel() {

    private var hasLoadedInitialData = false
    private var deletingNote: Note? = null

    init {
        viewModelScope.launch {
            noteRepository.fetchNotes()
        }
    }

    private val noteList = noteRepository.getNotes().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5_000L), emptyList()
    )

    private val _state = MutableStateFlow(NoteListState())
    val state = _state.combine(noteList) { state, notes ->
        Log.d("NoteListViewModel", "notes: $notes")
        state.copy(
            notes = notes
        )
    }.onStart {
        if (!hasLoadedInitialData) {
            /** Load initial data here **/
            initialize()
            hasLoadedInitialData = true
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = NoteListState()
    )

    private val _event = Channel<NoteListEvent>()
    val event = _event.receiveAsFlow()

    fun onAction(action: NoteListAction) {
        when (action) {
            is NoteListAction.OnHoldNoteItem -> onHoldNoteItem(action.note)
            NoteListAction.OnConfirmDeletingNote -> onConfirmDeletingNote()
            NoteListAction.OnDismissDeletingNote -> onDismissDeletingNote()
            NoteListAction.OnClickAddNote -> onClickAddNote()
            is NoteListAction.OnClickNote -> {  }
        }
    }

    private fun onConfirmDeletingNote() {
        viewModelScope.launch {
            deletingNote?.let { noteRepository.deleteNote(it) }
            _state.update { it.copy(isDeleteDialogShown = false) }
            deletingNote = null
        }
    }

    private fun onClickAddNote() {
        viewModelScope.launch {
            val result = noteRepository.createNote(
                note = Note(
                    id = UUID.randomUUID().toString(),
                    title = "New Note",
                    content = "",
                    createdAt = ZonedDateTime.now(),
                    lastEditedAt = ZonedDateTime.now(),
                )
            )

            when (result) {
                is Result.Error -> {
                    _event.send(NoteListEvent.OnError(result.error.asUiText()))
                }

                is Result.Success -> {
                    _event.send(NoteListEvent.OnCreateNewNote(result.data))
                }
            }
        }
    }

    private fun onDismissDeletingNote() {
        _state.update { it.copy(isDeleteDialogShown = false) }
        deletingNote = null
    }

    private fun onHoldNoteItem(note: Note) {
        deletingNote = note
        _state.update { it.copy(isDeleteDialogShown = true) }
    }


    fun initialize() {
        viewModelScope.launch {
            val authInfo = async {
                sessionStorage.get()?.username?.toProfileIconText()
            }
            _state.update { state ->
                state.copy(
                    userTag = authInfo.await() ?: ""
                )
            }
        }
    }

    fun String.toProfileIconText(): String {
        val list = this.split(" ")
        if (list.size == 1) {
            return list[0].take(2).uppercase()
        } else if (list.size == 2) {
            return list[0].take(1).uppercase() + list[1].take(1).uppercase()
        } else if (list.size == 3) {
            return list[0].take(1).uppercase() + list[1].take(1).uppercase()
        } else {
            throw Exception("Invalid name")
        }
    }

}