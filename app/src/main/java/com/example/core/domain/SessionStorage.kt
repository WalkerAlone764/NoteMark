package com.example.core.domain

import com.example.core.domain.model.AuthInfo

interface SessionStorage {
    suspend fun get(): AuthInfo?

    suspend fun set(authInfo: AuthInfo?)
}