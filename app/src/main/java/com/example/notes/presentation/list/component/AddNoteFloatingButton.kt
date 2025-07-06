package com.example.notes.presentation.list.component

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.presentation.designsystem.theme.NoteMarkTheme

@Composable
fun AddNoteFloatingButton(
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onClick,
        contentColor = Color.White,
        containerColor = Color.Transparent,
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp
        ),
        interactionSource = remember {
            MutableInteractionSource()
        },
        modifier = Modifier.background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xff58A1F8), Color(0xff5A4CF7)
                    ),
                ), shape = FloatingActionButtonDefaults.shape
            )
    ) {
        Icon(
            imageVector = Icons.Default.Add, contentDescription = null
        )
    }
}

@Preview
@Composable
fun AddNoteFloatingButtonPreview() {
    NoteMarkTheme {
        AddNoteFloatingButton(onClick = {})
    }
}
