package com.example.auth.domain.repository

import com.example.core.util.DataError
import com.example.core.util.EmptyDataResult

interface AuthRepository {

    suspend fun registration(username: String, email: String, password: String): EmptyDataResult<DataError.Network>
}