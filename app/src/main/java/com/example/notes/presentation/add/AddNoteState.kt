package com.example.notes.presentation.add

import com.example.notes.domain.model.Note

data class AddNoteState(
    val title: String = "",
    val content: String = ""
)