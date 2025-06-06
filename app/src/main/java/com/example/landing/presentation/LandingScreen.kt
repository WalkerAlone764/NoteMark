package com.example.landing.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.example.core.presentation.util.DeviceType
import com.example.core.presentation.util.getDeviceType
import com.example.landing.presentation.component.LandingScreenLandscape
import com.example.landing.presentation.component.LandingScreenMobilePortrait
import com.example.landing.presentation.component.LandscapeScreenTabletPortrait
import com.example.core.presentation.designsystem.theme.NoteMarkTheme

@Composable
fun LandingScreenRoot() {
    LandingScreen()
}

@Composable
fun LandingScreen() {
    val deviceType = getDeviceType()
    when (deviceType) {
        DeviceType.MOBILE_PORTRAIT -> {
            LandingScreenMobilePortrait()
        }

        DeviceType.TABLET_PORTRAIT -> LandscapeScreenTabletPortrait()
        DeviceType.LANDSCAPE -> LandingScreenLandscape()
    }
}


@PreviewScreenSizes
@Composable
private fun LandingScreenPreview() {
    NoteMarkTheme {
        LandingScreen()
    }
}