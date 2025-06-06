package com.example.auth.presentation.registration

data class RegistrationState(
    val username: String = "",
    val hasUsernameError: Boolean = false,
    val email: String = "",
    val hasEmailError: Boolean = false,
    val password: String = "",
    val hasPasswordError: Boolean = false,
    val confirmPassword: String = "",
    val hasConfirmPasswordError: Boolean = false,
    val isLoginEnabled: Boolean = false
)
