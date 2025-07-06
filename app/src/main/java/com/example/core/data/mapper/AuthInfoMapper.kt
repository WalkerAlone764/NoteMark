package com.example.core.data.mapper

import com.example.core.data.model.AuthInfoSerializable
import com.example.core.domain.model.AuthInfo

fun AuthInfo.toAuthInfoSerializable(): AuthInfoSerializable {
    return AuthInfoSerializable(
        username = username,
        accessToken = accessToken,
        refreshToken = refreshToken,
    )
}

fun AuthInfoSerializable.toAuthInfo(): AuthInfo {
    return AuthInfo(
        username = username,
        accessToken = accessToken,
        refreshToken = refreshToken,
    )
}