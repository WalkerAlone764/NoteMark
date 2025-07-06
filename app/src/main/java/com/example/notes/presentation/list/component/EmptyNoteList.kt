package com.example.notes.presentation.list.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EmptyNoteList(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = 45.dp
            ),
        contentAlignment = Alignment.TopCenter
    ) {
        Text(
            text = "You’ve got an empty board, let’s place your first note on it!",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.titleSmall.copy(
                fontSize = 17.sp
            ),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.W500,
            modifier = Modifier.widthIn(
                    max = 260.dp
                )
        )
    }
}