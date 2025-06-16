package com.example.landing.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.example.notemark.R
import com.example.core.presentation.designsystem.theme.NoteMarkTheme

@Composable
fun LandingScreenLandscape(
    navigateToRegistration: () -> Unit,
    navigateToLogin: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold { innerPadding ->
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE0EAFF)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.5f)
            ) {
                Image(
                    painter = painterResource(R.drawable.landing_background),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            Box(
                modifier = modifier
                    .fillMaxWidth(1f)
                    .fillMaxHeight(0.8f)
                    .clip(
                        RoundedCornerShape(
                            topStart = 22.dp,
                            bottomStart = 22.dp,
                        )
                    )
                    .background(Color.White)

            ) {

                LandingPageContent(
                    navigateToRegistration = navigateToRegistration,
                    navigateToLogin = navigateToLogin,
                    innerPadding = innerPadding
                )
            }

        }

    }

}


@PreviewScreenSizes
@Composable
private fun LandingScreenLandscapePreview() {
    NoteMarkTheme {
        LandingScreenLandscape(
            navigateToLogin = {},
            navigateToRegistration = {}
        )
    }
}