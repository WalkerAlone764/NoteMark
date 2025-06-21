package com.example.auth.presentation.registration

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auth.domain.UserDataValidator
import com.example.auth.domain.repository.AuthRepository
import com.example.core.domain.SessionStorage
import com.example.core.presentation.util.asUiText
import com.example.core.util.Result
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val authRepository: AuthRepository,
    private val sessionStorage: SessionStorage,
    private val userDataValidator: UserDataValidator
) : ViewModel() {

    init {
        viewModelScope.launch {
            Log.d("session", sessionStorage.get().toString())
        }
    }


    private val isLoginClicked = MutableStateFlow(false)

    private val _state = MutableStateFlow<RegistrationState>(RegistrationState())
    val state = combine(_state, isLoginClicked) { state, isLoading ->
        val updatedState = state.copy(
            isLoginEnabled = state.username.isNotEmpty() && state.email.isNotEmpty() && state.password.isNotEmpty() && state.confirmPassword.isNotEmpty()
        )

        if (isLoading) {
            val hasValidUsername = userDataValidator.isValidUsername(updatedState.username)
            val hasValidEmail = userDataValidator.isValidEmail(updatedState.email)
            val hasValidPassword = userDataValidator.isValidPassword(updatedState.password)
            val hasValidConfirmPassword = updatedState.password == updatedState.confirmPassword

           return@combine updatedState.copy(
                hasUsernameError = !hasValidUsername,
                hasEmailError = !hasValidEmail,
                hasPasswordError = !hasValidPassword,
                hasConfirmPasswordError = !hasValidConfirmPassword
            )
        }

        updatedState
    }
        .stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = RegistrationState()
        )

    private val _event = Channel<RegistrationEvent>()
    val event = _event.receiveAsFlow()


    fun onAction(action: RegistrationAction) {
        when (action) {
            RegistrationAction.OnLoginClicked -> onLoginClicked()
            is RegistrationAction.ChangeConfirmPassword -> onChangeConfirmPassword(action.confirmPassword)
            is RegistrationAction.ChangeEmail -> onChangeEmail(action.email)
            is RegistrationAction.ChangePassword -> onChangePassword(action.password)
            is RegistrationAction.ChangeUsername -> onChangeUsername(action.username)
        }

    }

    private fun onChangeUsername(username: String) {
        _state.update { it.copy(username = username) }
    }

    private fun onChangePassword(password: String) {
        _state.update { it.copy(password = password) }
    }

    private fun onChangeEmail(email: String) {
        _state.update { it.copy(email = email) }
    }

    private fun onChangeConfirmPassword(confirmPassword: String) {
        _state.update { it.copy(confirmPassword = confirmPassword) }
    }

    private fun onLoginClicked() {
        isLoginClicked.update { true }
        viewModelScope.launch {
            val hasValidUsername = userDataValidator.isValidUsername(_state.value.username)
            val hasValidEmail = userDataValidator.isValidEmail(_state.value.email)
            val hasValidPassword = userDataValidator.isValidPassword(_state.value.password)
            val hasValidConfirmPassword = _state.value.password == _state.value.confirmPassword

            _state.update {
                it.copy(
                    hasUsernameError = !hasValidUsername,
                    hasEmailError = !hasValidEmail,
                    hasPasswordError = !hasValidPassword,
                    hasConfirmPasswordError = !hasValidConfirmPassword

                )
            }

            if (!(hasValidUsername && hasValidEmail && hasValidPassword && hasValidConfirmPassword)) {

                return@launch
            }
            val result = authRepository.registration(
                username = _state.value.username,
                email = _state.value.email,
                password = _state.value.password
            )

             when(result) {
                is Result.Error -> {
                    _event.send(RegistrationEvent.OnError(result.error.asUiText()))
                }
                is Result.Success -> {
                    _event.send(RegistrationEvent.OnSuccessfullyRegistration)
                }
            }
        }
    }

}