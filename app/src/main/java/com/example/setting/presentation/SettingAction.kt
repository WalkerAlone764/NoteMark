package com.example.setting.presentation

sealed interface SettingAction {
    data object OnClickBack: SettingAction
    data object OnClickLogout: SettingAction

}