package com.example.setting.presentation

data class SettingState(
    val paramOne: String = "default",
    val paramTwo: List<String> = emptyList(),
)