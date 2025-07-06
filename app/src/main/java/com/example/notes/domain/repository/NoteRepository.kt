package com.example.notes.domain.repository

import com.example.core.util.DataError
import com.example.core.util.EmptyDataResult
import com.example.core.util.Result
import com.example.notes.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    suspend fun fetchNotes(): EmptyDataResult<DataError>
    fun getNotes(): Flow<List<Note>>

    suspend fun createNote(note: Note): Result<String,DataError>

    suspend fun deleteNote(note: Note): EmptyDataResult<DataError>
}