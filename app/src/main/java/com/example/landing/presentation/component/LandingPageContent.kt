package com.example.landing.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.core.designsystem.component.FilledButton
import com.example.core.designsystem.component.OutlinedButton

@Composable
fun LandingPageContent(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues = PaddingValues(0.dp)
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = modifier
                .fillMaxHeight()
                .background(Color.White, RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp))
                .padding(innerPadding)
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Your Own Collection of Notes",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Capture your thoughts and ideas.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                FilledButton(
                    text = "Get Started",
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()

                )
                OutlinedButton(
                    text = "Log In",
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }

    }
}