package com.example.auth.presentation.login
sealed interface LoginScreenAction {
    data class OnChangeEmail(val email: String): LoginScreenAction
    data class OnChangePassword(val password: String): LoginScreenAction
    data object OnClickLogin: LoginScreenAction
    data object OnClickNotHaveAnAccount: LoginScreenAction
}