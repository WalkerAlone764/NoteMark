package com.example.auth.data.repository

import com.example.auth.data.model.RegistrationRequest
import com.example.auth.domain.repository.AuthRepository
import com.example.core.networking.post
import com.example.core.networking.safeCall
import com.example.core.util.DataError
import com.example.core.util.EmptyDataResult
import io.ktor.client.HttpClient

class AuthRepositoryImpl(
    private val httpClient: HttpClient
): AuthRepository {
    override suspend fun registration(
        username: String,
        email: String,
        password: String
    ): EmptyDataResult<DataError.Network> {


          return  httpClient.post<RegistrationRequest, Unit>(
                route = "/api/auth/register",
                body =
                RegistrationRequest(
                    username = username,
                    email = email,
                    password = password
                )
            )

    }
}