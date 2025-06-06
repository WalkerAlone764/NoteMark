package com.example.auth.domain

class UserDataValidator(
    private val patternValidator: PatternValidator
) {

    fun isValidUsername(username: String): Boolean {
        return username.length in 3..20
    }

    fun isValidEmail(email: String): Boolean {
        return patternValidator.matches(email)
    }

    fun isValidPassword(password: String): Boolean {
        val hasMinLength = password.length >= MIN_PASSWORD_LENGTH
        val hasNumber = password.any { it.isDigit() }
        val hasSpecialCharacter = password.any { !it.isLetterOrDigit() }

        return hasNumber && hasSpecialCharacter && hasMinLength

    }


    companion object {
        const val MIN_PASSWORD_LENGTH = 8
    }
}