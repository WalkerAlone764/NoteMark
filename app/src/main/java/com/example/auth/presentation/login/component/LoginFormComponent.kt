package com.example.auth.presentation.login.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.auth.presentation.login.LoginScreenAction
import com.example.auth.presentation.login.LoginScreenState
import com.example.auth.presentation.registration.component.TextButton
import com.example.core.presentation.designsystem.component.FilledButton
import com.example.core.presentation.designsystem.component.OutlinedTextInput

@Composable
fun LoginFormComponent(
    state: LoginScreenState,
    onAction: (LoginScreenAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextInput(
            value = state.email,
            onValueChange = {
                onAction(LoginScreenAction.OnChangeEmail(it))
            },
            placeholder = "john.doe@example.com",
            label = "Email",
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text
            ),
            supportingText = if (state.hasEmailError) {
                "Invalid email provided"
            } else {
                null
            },
            hasError = state.hasEmailError,
            modifier = Modifier
                .fillMaxWidth()
        )

        OutlinedTextInput(
            value = state.password,
            onValueChange = {
                onAction(LoginScreenAction.OnChangePassword(it))
            },
            placeholder = "Password",
            label = "Password",
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),
            supportingText = if (state.hasPasswordError) {
                "Password must be at least 8 characters and include a number or symbol"
            } else {
                "Use 8+ characters with a number or symbol for better security"
            },
            isPassword = true,
            hasError = state.hasPasswordError,
            modifier = Modifier
                .fillMaxWidth()
        )

        FilledButton(
            text = "Login",
            onClick = {
                onAction(LoginScreenAction.OnClickLogin)
            },
            enabled = state.isLoginEnabled,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)

        )

        TextButton(
            text = "Don’t have an account?",
            onClick = {
                onAction(LoginScreenAction.OnClickNotHaveAnAccount)
            },
            modifier = Modifier
        )

    }
}