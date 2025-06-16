package com.example.auth.presentation.login.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.auth.presentation.login.LoginScreenAction
import com.example.auth.presentation.login.LoginScreenState
import com.example.core.presentation.designsystem.theme.NoteMarkTheme

@Composable
fun LoginMobilePortrait(
    state: LoginScreenState,
    onAction: (LoginScreenAction) -> Unit,
    modifier: Modifier = Modifier
) {

    Scaffold { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(
                    horizontal = 20.dp,
                    vertical = 20.dp
                )
        ) {
            LoginTitleComponent()
            Spacer(modifier = Modifier.height(28.dp))
            LoginFormComponent(
                state = state,
                onAction = onAction
            )
        }

    }
}

@Preview
@Composable
private fun LoginMobilePortraitPreview() {
    NoteMarkTheme {
        LoginMobilePortrait(
            state = LoginScreenState(),
            onAction = {}
        )
    }
}