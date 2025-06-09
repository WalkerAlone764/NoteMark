package com.example.core.di

import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.core.data.EncryptedSessionStorage
import com.example.core.data.networking.HttpClientFactory
import com.example.core.domain.SessionStorage
import io.ktor.client.HttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {
    single<HttpClient> {
        HttpClientFactory(get()).build()
    }

    single<SharedPreferences> {
        EncryptedSharedPreferences(
            androidApplication(),
            "auth_pref",
            MasterKey(
                androidApplication()
            ),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM

        )
    }

    single<SessionStorage> {
        EncryptedSessionStorage(get())
    }
}