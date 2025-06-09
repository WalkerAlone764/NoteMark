package com.example.core.domain.model

data class AuthInfo(
    val accessToken: String,
    val refreshToken: String,
    val userId: String,
)
