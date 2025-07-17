package com.example.notes.presentation.add

import com.example.core.presentation.util.UiText

sealed interface AddNoteEvent {
    data class OnError(val error: UiText) : AddNoteEvent
    data object SuccessfullyUpdated : AddNoteEvent
}