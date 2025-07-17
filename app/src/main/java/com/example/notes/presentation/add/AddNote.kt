package com.example.notes.presentation.add

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.presentation.designsystem.theme.NoteMarkTheme
import com.example.core.presentation.util.ObserveAsEvents
import com.example.core.presentation.util.negativePadding
import com.example.notes.presentation.add.component.AddNoteTopBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddNoteRoot(
    onBack: () -> Unit, viewModel: AddNoteViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current
    ObserveAsEvents(viewModel.event) { event ->
        when (event) {
            is AddNoteEvent.OnError -> {
                Toast.makeText(context, event.error.asString(context), Toast.LENGTH_SHORT).show()
            }

            AddNoteEvent.SuccessfullyUpdated -> {
                onBack()
            }
        }
    }

    AddNoteScreen(
        state = state, onClickBack = onBack, onAction = viewModel::onAction
    )
}

@Composable
fun AddNoteScreen(
    state: AddNoteState,
    onClickBack: () -> Unit,
    onAction: (AddNoteAction) -> Unit,
) {
    Scaffold(
        topBar = {
            AddNoteTopBar(
                onClickBack = onClickBack, onClickSaveNote = {
                    onAction(AddNoteAction.OnClickSaveNote)
                })
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
            TitleField(
                title = state.title, onChangeTitle = {
                    onAction(AddNoteAction.OnChangeTitle(it))
                })
            HorizontalDivider(
                modifier = Modifier
                    .padding(
                        vertical = 16.dp
                    )
                    .negativePadding(horizontal = 20.dp)
            )
            DescriptionField(
                content = state.content, onChangeContent = {
                    onAction(AddNoteAction.OnChangeContent(it))
                })
        }

    }
}


@Composable
private fun DescriptionField(
    content: String, onChangeContent: (String) -> Unit
) {
    BasicTextField(
        value = content,
        onValueChange = onChangeContent,
        textStyle = MaterialTheme.typography.bodyLarge.copy(
            color = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        decorationBox = { innerTextField ->
            if (content.isEmpty()) {
                Text(
                    text = "Tap to enter note content",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            innerTextField()
        },
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
private fun TitleField(
    title: String, onChangeTitle: (String) -> Unit
) {
    BasicTextField(
        value = title,
        onValueChange = onChangeTitle,
        textStyle = MaterialTheme.typography.titleMedium.copy(
            color = MaterialTheme.colorScheme.onSurface
        ),
        maxLines = 1,
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview
@Composable
private fun Preview() {
    NoteMarkTheme {
        AddNoteScreen(state = AddNoteState(), onClickBack = {}, onAction = {})
    }
}