package com.example.notes.domain.repository

import com.example.core.util.DataError
import com.example.core.util.Result
import com.example.notes.domain.model.Note

interface RemoteNoteDataSource {

    suspend fun getNotes(): Result<List<Note>, DataError>

    suspend fun createNote(note: Note): Result<Note, DataError>
    suspend fun updateNote(note: Note): Result<Note, DataError>
    suspend fun deleteNote(note: Note): Result<Unit, DataError>

}