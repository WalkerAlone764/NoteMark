package com.example.auth.data.repository

import com.example.auth.data.model.LoginRequest
import com.example.auth.data.model.LoginResponse
import com.example.auth.data.model.RegistrationRequest
import com.example.auth.domain.repository.AuthRepository
import com.example.core.data.networking.post
import com.example.core.domain.SessionStorage
import com.example.core.domain.model.AuthInfo
import com.example.core.util.DataError
import com.example.core.util.EmptyDataResult
import com.example.core.util.Result
import com.example.core.util.asEmptyDataResult
import io.ktor.client.HttpClient

class AuthRepositoryImpl(
    private val httpClient: HttpClient,
    private val sessionStorage: SessionStorage
) : AuthRepository {
    override suspend fun registration(
        username: String,
        email: String,
        password: String
    ): EmptyDataResult<DataError.Network> {

        return httpClient.post<RegistrationRequest, Unit>(
            route = "/api/auth/register",
            body =
                RegistrationRequest(
                    username = username,
                    email = email,
                    password = password
                )
        )

    }

    override suspend fun login(
        email: String,
        password: String
    ): EmptyDataResult<DataError.Network> {
        val result = httpClient.post<LoginRequest, LoginResponse>(
            route = "/api/auth/login",
            body = LoginRequest(
                email = email,
                password = password
            )
        )

        if (result is Result.Success) {
            sessionStorage.set(
                AuthInfo(
                    accessToken = result.data.accessToken,
                    refreshToken = result.data.refreshToken,

                )
            )
        }

        return result.asEmptyDataResult()
    }
}