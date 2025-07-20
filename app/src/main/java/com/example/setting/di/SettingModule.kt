package com.example.setting.di

import com.example.setting.data.repository.KtorRemoteSettingDataSource
import com.example.setting.data.repository.OfflineSettingRepository
import com.example.setting.data.repository.RoomLocalSettingDataSource
import com.example.setting.domain.repository.LocalSettingDataSource
import com.example.setting.domain.repository.RemoteSettingDataSource
import com.example.setting.domain.repository.SettingRepository
import com.example.setting.presentation.SettingViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val settingModule = module {
    single {
        OfflineSettingRepository(
            localSettingDataSource = get(),
            remoteSettingDataSource = get()
        )
    } bind SettingRepository::class

    single {
        RoomLocalSettingDataSource(
            sessionStorage = get()
        )
    } bind LocalSettingDataSource::class

    single {
        KtorRemoteSettingDataSource(
            httpClient = get(),
            sessionStorage = get()
        )
    } bind RemoteSettingDataSource::class

    viewModelOf(::SettingViewModel)
}