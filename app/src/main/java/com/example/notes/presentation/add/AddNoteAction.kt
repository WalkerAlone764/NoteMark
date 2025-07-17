package com.example.notes.presentation.add

sealed interface AddNoteAction {
    data object OnClickSaveNote: AddNoteAction
    data class OnChangeTitle(val title: String): AddNoteAction
    data class OnChangeContent(val content: String): AddNoteAction

}