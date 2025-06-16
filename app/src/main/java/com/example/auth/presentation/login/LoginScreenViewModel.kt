package com.example.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auth.domain.UserDataValidator
import com.example.auth.domain.repository.AuthRepository
import com.example.auth.presentation.login.LoginEvent
import com.example.core.presentation.util.asUiText
import com.example.core.util.Result
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginScreenViewModel(
    private val authRepository: AuthRepository,
    private val userDataValidator: UserDataValidator
) : ViewModel() {

    private var hasLoadedInitialData = false
    private val isLoginClicked = MutableStateFlow(false)

    private val _state = MutableStateFlow(LoginScreenState())
    val state = combine(_state,isLoginClicked) { state, isLoginClicked ->

        val updatedState = state.copy(
            isLoginEnabled = state.email.isNotEmpty() && state.password.isNotEmpty()
        )
        if (isLoginClicked) {
            val hasValidEmail = userDataValidator.isValidEmail(updatedState.email)
            val hasValidPassword = userDataValidator.isValidPassword(updatedState.password)

            return@combine updatedState.copy(
                hasEmailError = !hasValidEmail,
                hasPasswordError = !hasValidPassword,
            )
        }
        updatedState
    }
        .onStart {
            if (!hasLoadedInitialData) {
                /** Load initial data here **/
                hasLoadedInitialData = true
            }
        }

        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = LoginScreenState()
        )

    private val _event = Channel<LoginEvent>()
    val event = _event.receiveAsFlow()

    fun onAction(action: LoginScreenAction) {
        when (action) {
            is LoginScreenAction.OnChangeEmail -> onChangeEmail(action.email)
            is LoginScreenAction.OnChangePassword -> onChangePassword(action.password)
            LoginScreenAction.OnClickLogin -> onClickLogin()
            LoginScreenAction.OnClickNotHaveAnAccount -> onClickNotHaveAnAccount()
        }
    }

    private fun onClickNotHaveAnAccount() {
        viewModelScope.launch {
            _event.send(LoginEvent.OnClickNotHaveAnAccount)
        }
    }

    private fun onClickLogin() {
        viewModelScope.launch {
            isLoginClicked.update { true }
            val hasValidEmail = userDataValidator.isValidEmail(_state.value.email)
            val hasValidPassword = userDataValidator.isValidPassword(_state.value.password)
            _state.update {
                it.copy(
                    hasEmailError = !hasValidEmail,
                    hasPasswordError = !hasValidPassword
                )
            }

            if (!(hasValidPassword && hasValidEmail)) {
                return@launch
            }

            val result = authRepository.login(
                email = _state.value.email,
                password = _state.value.password
            )

            when(result) {
                is Result.Error -> {
                    _event.send(LoginEvent.OnError(result.error.asUiText()))
                }
                is Result.Success -> {
                    _event.send(LoginEvent.OnSuccess)
                }
            }


        }
    }

    private fun onChangePassword(password: String) {
        _state.update { it.copy(password = password) }
    }

    private fun onChangeEmail(email: String) {
        _state.update { it.copy(email = email) }
    }

}