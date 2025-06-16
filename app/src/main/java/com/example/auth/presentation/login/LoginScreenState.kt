package com.example.auth.presentation.login

data class LoginScreenState(
    val email: String = "",
    val hasEmailError: Boolean = false,
    val password: String = "",
    val hasPasswordError: Boolean = false,
    val isLoginEnabled: Boolean = false
)