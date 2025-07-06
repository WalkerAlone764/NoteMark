package com.example.notes.di

import androidx.room.Room
import com.example.notes.data.NoteDatabase
import com.example.notes.data.repository.KtorRemoteNoteDataSource
import com.example.notes.data.repository.OfflineNoteRepository
import com.example.notes.data.repository.RoomLocalNoteDataSource
import com.example.notes.domain.repository.LocalNoteDataSource
import com.example.notes.domain.repository.NoteRepository
import com.example.notes.domain.repository.RemoteNoteDataSource
import com.example.notes.presentation.list.NoteListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val noteModule = module {
    viewModelOf(::NoteListViewModel)

    single<NoteDatabase> {
        Room.databaseBuilder(
            androidContext(),
            NoteDatabase::class.java,
            "notes.db"
        ).build()
    }

    single<RemoteNoteDataSource> {
        KtorRemoteNoteDataSource(get(), get())
    }

    single<LocalNoteDataSource> {
        RoomLocalNoteDataSource(get())
    }

    single<NoteRepository> {
        OfflineNoteRepository(get(), get())
    }
}