package com.example.auth.presentation.login.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.auth.presentation.login.LoginScreenAction
import com.example.auth.presentation.login.LoginScreenState
import com.example.core.presentation.designsystem.theme.NoteMarkTheme

@Composable
fun LoginLandscape(
    state: LoginScreenState,
    onAction: (LoginScreenAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold { innerPadding ->

        Row(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(
                    vertical = 12.dp,
                    horizontal = 20.dp
                )
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                LoginTitleComponent()
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                LoginFormComponent(
                    state = state,
                    onAction = onAction
                )
            }
        }

    }
}

@Preview(
    device = "spec:width=393dp,height=830dp,dpi=480,orientation=landscape"
)
@Composable
private fun LoginLandscapePreview() {
    NoteMarkTheme {
        LoginLandscape(
            state = LoginScreenState(),
            onAction = {}
        )
    }
}