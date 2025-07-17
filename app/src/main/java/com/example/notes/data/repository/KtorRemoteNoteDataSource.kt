package com.example.notes.data.repository

import android.util.Log
import androidx.compose.ui.util.fastMap
import com.example.core.data.networking.delete
import com.example.core.data.networking.get
import com.example.core.data.networking.post
import com.example.core.data.networking.put
import com.example.core.util.DataError
import com.example.core.util.EmptyDataResult
import com.example.core.util.Result
import com.example.core.util.map
import com.example.notes.data.NoteDatabase
import com.example.notes.data.dto.NoteDto
import com.example.notes.data.mapper.toNote
import com.example.notes.data.mapper.toNoteDto
import com.example.notes.data.repository.model.GetNotesResponse
import com.example.notes.domain.model.Note
import com.example.notes.domain.repository.RemoteNoteDataSource
import io.ktor.client.HttpClient
import timber.log.Timber

class KtorRemoteNoteDataSource(
    private val database: NoteDatabase, private val httpClient: HttpClient
) : RemoteNoteDataSource {

    private val noteDao = database.noteDao

    override suspend fun getNotes(): Result<List<Note>, DataError> {
        return httpClient.get<GetNotesResponse>(
            route = "/api/notes"
        ).map {
            it.notes.fastMap { note -> note.toNote() }
        }


    }

    override suspend fun createNote(note: Note): Result<Note, DataError> {
        val result = httpClient.post<NoteDto, NoteDto>(
            route = "/api/notes", body = note.toNoteDto()
        ).map {
            it.toNote()
        }

        Log.d("KtorRemoteNoteDataSource", "createNote: $result")
        return result
    }

    override suspend fun updateNote(note: Note): Result<Note, DataError> {
        val result = httpClient.put<NoteDto, NoteDto>(
            route = "/api/notes",
            body = note.toNoteDto()
            ).map {
            it.toNote()
        }
        Log.d("update note ktor", result.toString())
        return result
    }

    override suspend fun deleteNote(note: Note): EmptyDataResult<DataError> {
       return httpClient.delete(
           route = "/api/notes/${note.id}",
           queryParameters = mapOf(
               "id" to note.id
           )
       )
    }
}