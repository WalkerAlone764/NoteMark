package com.example.core.di

import com.example.core.networking.HttpClientFactory
import io.ktor.client.HttpClient
import org.koin.dsl.module

val appModule = module {
    single<HttpClient> {
        HttpClientFactory().build()
    }
}