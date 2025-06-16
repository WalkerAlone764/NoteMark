package com.example.landing.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices.TABLET
import androidx.compose.ui.tooling.preview.Preview
import com.example.notemark.R
import com.example.core.presentation.designsystem.theme.NoteMarkTheme

@Composable
fun LandscapeScreenTabletPortrait(
    navigateToRegistration: () -> Unit,
    navigateToLogin: () -> Unit
) {
    Scaffold { innerPadding ->
        Box {
            Image(
                painter = painterResource(R.drawable.landing_background),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
            )
        }

        LandingPageContent(
            navigateToRegistration = navigateToRegistration,
            navigateToLogin = navigateToLogin,
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .fillMaxWidth(0.6f),
            innerPadding = innerPadding
        )
    }
}

@Preview(device = TABLET)
@Composable
private fun LandscapeScreenTabletPortraitPreview() {
    NoteMarkTheme {
        LandscapeScreenTabletPortrait(
            navigateToLogin = {},
            navigateToRegistration = {}
        )
    }
}