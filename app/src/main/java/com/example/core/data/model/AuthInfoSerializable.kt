package com.example.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class AuthInfoSerializable(
    val username: String,
    val accessToken: String,
    val refreshToken: String,
)
