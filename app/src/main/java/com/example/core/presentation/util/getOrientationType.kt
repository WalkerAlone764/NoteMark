package com.example.core.presentation.util

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun getOrientationType(): OrientationType {
    val localConfiguration = LocalConfiguration.current

    val orientation = localConfiguration.orientation

    val orientationType = when(orientation) {
        Configuration.ORIENTATION_PORTRAIT -> {
            OrientationType.PORTRAIT
        }

        Configuration.ORIENTATION_LANDSCAPE -> {
            OrientationType.LANDSCAPE
        }
        else -> {
            OrientationType.PORTRAIT

        }
    }
    return orientationType
}