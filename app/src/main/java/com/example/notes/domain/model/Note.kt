package com.example.notes.domain.model

import java.time.ZonedDateTime

data class Note(
    val id: String,
    val title: String,
    val content: String,
    val createdAt: ZonedDateTime,
    val lastEditedAt: ZonedDateTime
)
