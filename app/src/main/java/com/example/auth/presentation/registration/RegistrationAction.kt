package com.example.auth.presentation.registration

sealed interface RegistrationAction {
    data object OnLoginClicked: RegistrationAction
    data class ChangeUsername(val username: String): RegistrationAction
    data class ChangeEmail(val email: String): RegistrationAction
    data class ChangePassword(val password: String): RegistrationAction
    data class ChangeConfirmPassword(val confirmPassword: String): RegistrationAction
}