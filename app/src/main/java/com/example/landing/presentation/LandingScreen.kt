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
fun LandingScreenRoot(
    navigateToRegistration: () -> Unit,
    navigateToLogin: () -> Unit
) {
    LandingScreen(
        navigateToRegistration = navigateToRegistration,
        navigateToLogin = navigateToLogin
    )
}

@Composable
fun LandingScreen(
    navigateToRegistration: () -> Unit,
    navigateToLogin: () -> Unit
) {
    val deviceType = getDeviceType()
    when (deviceType) {
        DeviceType.MOBILE_PORTRAIT -> {
            LandingScreenMobilePortrait(
                navigateToRegistration = navigateToRegistration,
                navigateToLogin = navigateToLogin
            )
        }

        DeviceType.TABLET_PORTRAIT -> LandscapeScreenTabletPortrait(
            navigateToRegistration = navigateToRegistration,
            navigateToLogin = navigateToLogin
        )
        DeviceType.LANDSCAPE -> LandingScreenLandscape(
            navigateToRegistration = navigateToRegistration,
            navigateToLogin = navigateToLogin
        )
    }
}


@PreviewScreenSizes
@Composable
private fun LandingScreenPreview() {
    NoteMarkTheme {
        LandingScreen(
            navigateToRegistration = {},
            navigateToLogin = {}
        )
    }
}