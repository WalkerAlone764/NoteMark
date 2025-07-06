package com.example.notes.data.repository

import android.database.sqlite.SQLiteFullException
import androidx.compose.ui.util.fastMap
import com.example.core.util.DataError
import com.example.core.util.Result
import com.example.notes.data.NoteDatabase
import com.example.notes.data.mapper.toNote
import com.example.notes.data.mapper.toNoteEntity
import com.example.notes.domain.model.Note
import com.example.notes.domain.repository.LocalNoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomLocalNoteDataSource(
    private val database: NoteDatabase
) : LocalNoteDataSource {
    private val noteDao = database.noteDao

    override  fun getNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes()
            .map { list -> list.fastMap { noteEntity -> noteEntity.toNote() } }
    }

    override suspend fun getNoteById(id: String): Note {
        TODO("Not yet implemented")
    }

    override suspend fun upsertNote(note: Note): Result<String, DataError.Local> {
        return try {
            val entity = note.toNoteEntity()
            noteDao.upsertNote(entity)
            Result.Success(entity.id)
        } catch (e: SQLiteFullException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun upsertsNotes(notes: List<Note>): Result<List<String>, DataError.Local> {
        return try {
            val entities = notes.fastMap { note ->  note.toNoteEntity() }
            noteDao.upsertNotes(entities)
            Result.Success(entities.map { it.id })
        } catch (e: SQLiteFullException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note.id)
    }
}