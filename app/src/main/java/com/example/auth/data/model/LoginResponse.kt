package com.example.auth.data.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val username: String,
    val accessToken: String,
    val refreshToken: String
)