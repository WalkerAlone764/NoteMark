package com.example.notes.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val title: String,
    val content: String,
    val createdAt: String,
    val lastEditedAt: String
)
