package com.example.setting.data.repository

import com.example.core.util.DataError
import com.example.core.util.EmptyDataResult
import com.example.setting.domain.repository.LocalSettingDataSource
import com.example.setting.domain.repository.RemoteSettingDataSource
import com.example.setting.domain.repository.SettingRepository

class OfflineSettingRepository(
    private val localSettingDataSource: LocalSettingDataSource,
    private val remoteSettingDataSource: RemoteSettingDataSource
): SettingRepository {

    override suspend fun logout(): EmptyDataResult<DataError> {
        localSettingDataSource.logout()
       return remoteSettingDataSource.logout()
    }
}