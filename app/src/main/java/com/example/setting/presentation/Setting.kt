@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.setting.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.core.presentation.designsystem.theme.NoteMarkTheme
import com.example.notemark.R

@Composable
fun SettingRoot(
    navigateBack: () -> Unit, viewModel: SettingViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SettingScreen(
        state = state, onAction = { action ->
            when (action) {
                SettingAction.OnClickBack -> {
                    navigateBack()
                }

                else -> {
                    viewModel.onAction(action)
                }
            }

        })
}

@Composable
fun SettingScreen(
    state: SettingState,
    onAction: (SettingAction) -> Unit,
) {
    Scaffold(
        topBar = {
            SettingTopBar(
                onClickBack = {
                    onAction(SettingAction.OnClickBack)
                })
        }) { innerPadding ->
        SettingContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )

    }
}

@Composable
fun SettingTopBar(
    onClickBack: () -> Unit, modifier: Modifier = Modifier
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.background,
    ), navigationIcon = {
        IconButton(
            onClick = onClickBack
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                contentDescription = "navigate back",
                modifier = Modifier
                    .padding(start = 6.dp)
                    .size(20.dp)
            )
        }
    }, title = {
        Text(
            text = "SETTINGS", style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 16.sp
            ), color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }, modifier = modifier
    )
}

@Composable
private fun SettingContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            IconButton(
                onClick = {}) {
                Icon(
                    painter = painterResource(R.drawable.logout),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error
                )
            }

            Text(
                text = "Log out", style = MaterialTheme.typography.titleSmall.copy(
                    fontSize = 17.sp, color = MaterialTheme.colorScheme.error
                )
            )
        }
    }

}

@Preview
@Composable
private fun Preview() {
    NoteMarkTheme {
        SettingScreen(
            state = SettingState(), onAction = {})
    }
}