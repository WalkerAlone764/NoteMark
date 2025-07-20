package com.example.setting.data.repository

import com.example.core.data.networking.post
import com.example.core.domain.SessionStorage
import com.example.core.util.DataError
import com.example.core.util.EmptyDataResult
import com.example.core.util.Result
import com.example.core.util.asEmptyDataResult
import com.example.setting.data.dto.LogoutRequest
import com.example.setting.domain.repository.RemoteSettingDataSource
import io.ktor.client.HttpClient

class KtorRemoteSettingDataSource(
    private val httpClient: HttpClient, private val sessionStorage: SessionStorage
) : RemoteSettingDataSource {

    override suspend fun logout(): EmptyDataResult<DataError> {
        val accessToken = sessionStorage.get()?.accessToken

        return if (accessToken != null) {
            val result = httpClient.post<LogoutRequest, Unit>(
                route = "/api/auth/logout", body = LogoutRequest(accessToken)
            )
            result.asEmptyDataResult()
        } else {
            Result.Error(DataError.Network.UNAUTHORIZED)
        }

    }
}