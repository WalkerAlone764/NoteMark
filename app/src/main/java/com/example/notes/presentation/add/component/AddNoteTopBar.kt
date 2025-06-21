@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.notes.presentation.add.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.presentation.designsystem.theme.NoteMarkTheme

@Composable
fun AddNoteTopBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier,
        navigationIcon = {
            IconButton(
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "navigate back",
                    modifier = Modifier
                        .size(20.dp)
                )
            }
        },
        title = {},
        actions = {
            TextButton(
                onClick = {}
            ) {
                Text(
                    text = "SAVE NOTE",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontSize = 16.sp
                    ),
                    fontWeight = FontWeight.W700
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            navigationIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    )
}

@Preview
@Composable
private fun NoteListTopBarPreview() {
    NoteMarkTheme {
        AddNoteTopBar()
    }
}