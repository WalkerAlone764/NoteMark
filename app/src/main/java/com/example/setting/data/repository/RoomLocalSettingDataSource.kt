package com.example.setting.data.repository

import com.example.core.domain.SessionStorage
import com.example.setting.domain.repository.LocalSettingDataSource

class RoomLocalSettingDataSource(
    private val sessionStorage: SessionStorage
) : LocalSettingDataSource {

    override suspend fun logout() {
        sessionStorage.set(null)
    }
}