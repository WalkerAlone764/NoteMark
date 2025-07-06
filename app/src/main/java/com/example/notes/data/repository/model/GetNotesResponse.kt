package com.example.notes.data.repository.model

import com.example.notes.data.dto.NoteDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetNotesResponse(
    @SerialName("notes")
    val notes: List<NoteDto>,
    @SerialName("total")
    val total: Int,
)
