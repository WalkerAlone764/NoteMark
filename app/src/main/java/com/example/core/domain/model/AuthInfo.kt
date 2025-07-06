package com.example.core.domain.model

data class AuthInfo(
    val username: String,
    val accessToken: String,
    val refreshToken: String,
)
