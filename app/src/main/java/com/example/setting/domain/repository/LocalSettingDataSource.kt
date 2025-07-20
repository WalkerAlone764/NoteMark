package com.example.setting.domain.repository

import com.example.core.util.EmptyDataResult

interface LocalSettingDataSource {

    suspend fun logout()
}