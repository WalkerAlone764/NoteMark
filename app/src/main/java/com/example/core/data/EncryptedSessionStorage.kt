package com.example.core.data

import android.content.SharedPreferences
import com.example.core.data.mapper.toAuthInfo
import com.example.core.data.mapper.toAuthInfoSerializable
import com.example.core.data.model.AuthInfoSerializable
import com.example.core.domain.SessionStorage
import com.example.core.domain.model.AuthInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import androidx.core.content.edit

class EncryptedSessionStorage(
    private val sharedPreferences: SharedPreferences,
) : SessionStorage {
    override suspend fun get(): AuthInfo? {
        return withContext(Dispatchers.IO) {
            val json = sharedPreferences.getString(KEY_AUTH_INFO, null)
            if (json == null) {
                return@withContext null
            } else {
                return@withContext Json.decodeFromString<AuthInfoSerializable>(json).toAuthInfo()
            }
        }
    }

    override suspend fun set(authInfo: AuthInfo?) {
        withContext(Dispatchers.IO) {
            if (authInfo == null) {
                sharedPreferences.edit(commit = true) { remove(KEY_AUTH_INFO) }
                return@withContext
            } else {
                val json = Json.encodeToString(authInfo.toAuthInfoSerializable())
                sharedPreferences.edit(commit = true) {
                    putString(KEY_AUTH_INFO, json)
                }

            }
        }
    }

    companion object {
        private const val KEY_AUTH_INFO = "auth_info"
    }
}