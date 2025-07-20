package com.example.setting.domain.repository

import com.example.core.util.DataError
import com.example.core.util.EmptyDataResult

interface SettingRepository {

    suspend fun logout(): EmptyDataResult<DataError>
}