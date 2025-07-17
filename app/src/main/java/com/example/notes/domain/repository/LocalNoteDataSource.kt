package com.example.notes.domain.repository

import com.example.core.util.DataError
import com.example.core.util.Result
import com.example.notes.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface LocalNoteDataSource {
    fun getNotes(): Flow<List<Note>>
    suspend fun getNoteById(id: String): Note?
    suspend fun upsertNote(note: Note): Result<String, DataError.Local>

    suspend fun upsertsNotes(notes: List<Note>): Result<List<String>, DataError.Local>
    suspend fun deleteNote(note: Note)
}