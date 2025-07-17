package com.example.notes.presentation.list

import com.example.notes.domain.model.Note

sealed interface NoteListAction {
    data class OnHoldNoteItem(val note: Note) : NoteListAction
    data object OnDismissDeletingNote : NoteListAction
    data object OnConfirmDeletingNote : NoteListAction
    data object OnClickAddNote : NoteListAction
    data class OnClickNote(val note: Note) : NoteListAction
    data object OnClickSettingIcon : NoteListAction
}