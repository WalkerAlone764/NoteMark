package com.example.notemark

import android.app.Application
import com.example.auth.di.authModule
import com.example.core.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NoteMarkApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {

            androidLogger()
            androidContext(this@NoteMarkApplication)
            modules(appModule, authModule)
        }
    }
}