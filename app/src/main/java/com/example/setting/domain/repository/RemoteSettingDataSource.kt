package com.example.setting.domain.repository

import com.example.core.util.DataError
import com.example.core.util.EmptyDataResult

interface RemoteSettingDataSource {

    suspend fun logout(): EmptyDataResult<DataError>
}