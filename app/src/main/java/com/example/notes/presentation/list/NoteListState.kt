package com.example.notes.presentation.list

import com.example.notes.domain.model.Note

data class NoteListState(
    val userTag: String = "",
    val notes: List<Note> = emptyList(),
    val isDeleteDialogShown: Boolean = false
)