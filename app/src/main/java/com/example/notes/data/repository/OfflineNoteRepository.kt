package com.example.notes.data.repository

import android.util.Log
import com.example.core.util.DataError
import com.example.core.util.EmptyDataResult
import com.example.core.util.Result
import com.example.core.util.asEmptyDataResult
import com.example.notes.domain.model.Note
import com.example.notes.domain.repository.LocalNoteDataSource
import com.example.notes.domain.repository.NoteRepository
import com.example.notes.domain.repository.RemoteNoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.UUID

class OfflineNoteRepository(
    private val localNoteDataSource: LocalNoteDataSource,
    private val remoteNoteDataSource: RemoteNoteDataSource
) : NoteRepository {

    private val applicationScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    override suspend fun fetchNotes(): EmptyDataResult<DataError> {
        val result = remoteNoteDataSource.getNotes()
        Log.d("fetch result", result.toString())
        return when (result) {
            is Result.Error -> result.asEmptyDataResult()
            is Result.Success -> {
                applicationScope.async {
                    Log.d("get size", result.data.size.toString())
                    localNoteDataSource.upsertsNotes(result.data)
                }.await().asEmptyDataResult()
            }
        }
    }

    override fun getNotes(): Flow<List<Note>> {
        return localNoteDataSource.getNotes()
    }

    override suspend fun getNoteById(id: String): Note? {
        return localNoteDataSource.getNoteById(id)
    }

    override suspend fun createNote(note: Note): Result<String,DataError> {
        val localNoteId = localNoteDataSource.upsertNote(note)
        Log.d("NoteRepository local", localNoteId.toString())

        if (localNoteId !is Result.Success) {
            return localNoteId
        }

         applicationScope.launch {
            val noteWithId = note.copy(id = localNoteId.data)
            val remoteResult = remoteNoteDataSource.createNote(noteWithId)
            Log.d("NoteRepository remote",remoteResult.toString())
//            when (remoteResult) {
//                is Result.Error -> {
//                     Result.Success(noteWithId.id)
//                }
//
//                is Result.Success -> {
//                     localNoteDataSource.upsertNote(remoteResult.data)
//
//                }
//            }
        }

        return Result.Success(localNoteId.data)
    }

    override suspend fun deleteNote(note: Note): EmptyDataResult<DataError> {
        localNoteDataSource.deleteNote(note)
         applicationScope.launch {
            val result = remoteNoteDataSource.deleteNote(note)
            Log.d("result", result.toString())
//            when(result) {
//                is Result.Error -> {
//                    return@async result
//                }
//                is Result.Success -> {
//                    return@async result.asEmptyDataResult()
//                }
//            }
        }

        return Result.Success(Unit)
    }

    override suspend fun updateNote(note: Note): EmptyDataResult<DataError> {
        val localResult = localNoteDataSource.upsertNote(note)
        return when(localResult) {
            is Result.Error -> {
                localResult.asEmptyDataResult()
            }
            is Result.Success -> {
                val result = remoteNoteDataSource.updateNote(note)
//                when(result) {
//                    is Result.Error -> {}
//                    is Result.Success -> {}
//                }

                 Result.Success(Unit)
            }
        }

    }
}