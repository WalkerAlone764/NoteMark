package com.example.notes.presentation.add

data class AddNoteState(
    val paramOne: String = "default",
    val paramTwo: List<String> = emptyList(),
)