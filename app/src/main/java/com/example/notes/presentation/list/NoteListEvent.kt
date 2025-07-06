package com.example.notes.presentation.list

import com.example.core.presentation.util.UiText

sealed interface NoteListEvent {
    data class OnCreateNewNote(val noteId: String) : NoteListEvent
    data class OnError(val uiText: UiText) : NoteListEvent
}