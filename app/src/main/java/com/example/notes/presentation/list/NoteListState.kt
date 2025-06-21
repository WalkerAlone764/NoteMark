package com.example.notes.presentation.list

data class NoteListState(
    val paramOne: String = "default",
    val paramTwo: List<String> = emptyList(),
)