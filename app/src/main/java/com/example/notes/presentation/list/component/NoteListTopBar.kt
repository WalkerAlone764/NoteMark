@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.notes.presentation.list.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.presentation.designsystem.theme.NoteMarkTheme

@Composable
fun NoteListTopBar(
    tag: String,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = "NoteMark",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 20.sp
                ),
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.W700,
            )
        },
        actions = {
            Tag(
                tag = tag
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            navigationIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    )
}

@Composable
private fun Tag(
    tag: String
) {
    Box(
        modifier = Modifier
            .wrapContentSize()

            .clip(RoundedCornerShape(30))
            .background(MaterialTheme.colorScheme.primary)
            .padding(
                12.dp
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = tag,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.W700,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Preview
@Composable
private fun Preview() {
    NoteMarkTheme {
        NoteListTopBar(
            tag = "JS"
        )
    }
}