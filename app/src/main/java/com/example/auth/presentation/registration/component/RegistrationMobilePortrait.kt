package com.example.auth.presentation.registration.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.auth.presentation.registration.RegistrationAction
import com.example.auth.presentation.registration.RegistrationState

@Composable
fun RegistrationMobilePortrait(
    state: RegistrationState,
    onAction: (RegistrationAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold { innerPadding ->
        Box(
            modifier = modifier
                .background(MaterialTheme.colorScheme.primary)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .padding(
                        top = 12.dp
                    )
                    .background(Color.White, RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    .padding(
                        horizontal = 20.dp
                    )
                    .verticalScroll(rememberScrollState())
                    .imePadding()

                    .padding(innerPadding)

            ) {
                RegistrationHeader()
                Spacer(
                    modifier = Modifier
                        .height(20.dp)
                )
                RegistrationForm(
                    state = state,
                    onAction = onAction,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }
}