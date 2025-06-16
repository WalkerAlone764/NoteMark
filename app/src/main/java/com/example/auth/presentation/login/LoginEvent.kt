package com.example.auth.presentation.login

import com.example.core.presentation.util.UiText

sealed interface LoginEvent {
    data object OnSuccess : LoginEvent
    data class OnError(val error: UiText) : LoginEvent
    data object OnClickNotHaveAnAccount : LoginEvent
}