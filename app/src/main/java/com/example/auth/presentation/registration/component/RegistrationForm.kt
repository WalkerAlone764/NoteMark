package com.example.auth.presentation.registration.component

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
import com.example.auth.presentation.registration.RegistrationAction
import com.example.auth.presentation.registration.RegistrationState
import com.example.core.presentation.designsystem.component.FilledButton
import com.example.core.presentation.designsystem.component.OutlinedTextInput

@Composable
fun RegistrationForm(
    state: RegistrationState,
    onAction: (RegistrationAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextInput(
            value = state.username,
            onValueChange = {
                onAction(RegistrationAction.ChangeUsername(it))
            },
            placeholder = "John.Doe",
            label = "Username",
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text
            ),
            supportingText = if (state.hasUsernameError) {
                "Username must be at least 3 characters"
            } else {
                "Use between 3 and 20 characters for your username"
            },
            hasError = state.hasUsernameError,
            modifier = Modifier
                .fillMaxWidth()
        )
        OutlinedTextInput(
            value = state.email,
            onValueChange = {
                onAction(RegistrationAction.ChangeEmail(it))
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
                onAction(RegistrationAction.ChangePassword(it))
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

        OutlinedTextInput(
            value = state.confirmPassword,
            onValueChange = {
                onAction(RegistrationAction.ChangeConfirmPassword(it))
            },
            placeholder = "Password",
            label = "Repeat Password",
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),
            supportingText = if (state.hasConfirmPasswordError) {
                "Passwords do not match"
            } else {
                null
            },
            isPassword = true,
            hasError = state.hasConfirmPasswordError,
            modifier = Modifier
                .fillMaxWidth()
        )

        FilledButton(
            text = "Log in",
            onClick = {
                onAction(RegistrationAction.OnLoginClicked)
            },
            enabled = state.isLoginEnabled,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)

        )

        TextButton(
            text = "Don’t have an account?",
            modifier = Modifier
        )
    }
}