package com.example.notes.presentation.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.core.presentation.designsystem.theme.NoteMarkTheme
import com.example.core.presentation.util.negativePadding
import com.example.notes.presentation.add.component.AddNoteTopBar

@Composable
fun AddNoteRoot(
    viewModel: AddNoteViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    AddNoteScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun AddNoteScreen(
    state: AddNoteState,
    onAction: (AddNoteAction) -> Unit,
) {
    Scaffold(
        topBar = {
            AddNoteTopBar()
        }, modifier = Modifier

    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(
                    horizontal = 20.dp
                )
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            TitleField()
            HorizontalDivider(
                modifier = Modifier
                    .padding(
                        vertical = 16.dp
                    )
                    .negativePadding(horizontal = 20.dp)
            )
            DescriptionField()
        }

    }
}


@Composable
private fun DescriptionField() {
    BasicTextField(
        value = "Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. ",
        onValueChange = {},
        textStyle = MaterialTheme.typography.bodyLarge.copy(
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    )
}

@Composable
private fun TitleField() {
    BasicTextField(
        value = "Note Title",
        onValueChange = {},
        textStyle = MaterialTheme.typography.titleMedium.copy(
            color = MaterialTheme.colorScheme.onSurface
        ),
        maxLines = 1
    )
}

@Preview
@Composable
private fun Preview() {
    NoteMarkTheme {
        AddNoteScreen(
            state = AddNoteState(),
            onAction = {}
        )
    }
}