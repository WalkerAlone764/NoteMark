package com.example.core.presentation.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val LightColorScheme = lightColorScheme(
    primary = Primary,
    surface = Surface,
    background = Color.White,
    surfaceDim = SurfaceLowest,
    onSurface = OnSurface,
    surfaceVariant = SurfaceVariant,
    error = Error,
    onPrimary = OnPrimary,
)

@Composable
fun NoteMarkTheme(
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}