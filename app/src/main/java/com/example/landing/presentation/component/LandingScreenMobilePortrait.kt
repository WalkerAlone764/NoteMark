package com.example.landing.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.notemark.R

@Composable
internal fun LandingScreenMobilePortrait(modifier: Modifier = Modifier) {
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
            modifier = Modifier
                .height(IntrinsicSize.Min),
            innerPadding = innerPadding
        )
    }

}