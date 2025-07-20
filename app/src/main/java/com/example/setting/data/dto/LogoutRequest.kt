package com.example.setting.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class LogoutRequest(
    val refreshToken: String
)
