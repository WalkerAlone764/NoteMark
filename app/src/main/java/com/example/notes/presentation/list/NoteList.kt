@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.notes.presentation.list

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.presentation.designsystem.theme.NoteMarkTheme
import com.example.core.presentation.util.DeviceType
import com.example.core.presentation.util.ObserveAsEvents
import com.example.core.presentation.util.getDeviceType
import com.example.notes.domain.model.Note
import com.example.notes.presentation.list.component.AddNoteFloatingButton
import com.example.notes.presentation.list.component.EmptyNoteList
import com.example.notes.presentation.list.component.NoteItem
import com.example.notes.presentation.list.component.NoteListTopBar
import org.koin.androidx.compose.koinViewModel
import java.time.ZonedDateTime

@Composable
fun NoteListRoot(
    onNavigateToAddNote: (noteId: String) -> Unit,
    viewModel: NoteListViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current
    ObserveAsEvents(viewModel.event) { event ->
        when (event) {
            is NoteListEvent.OnCreateNewNote -> {
                onNavigateToAddNote(event.noteId)
            }

            is NoteListEvent.OnError -> {
                Toast.makeText(context,event.uiText.asString(context), Toast.LENGTH_SHORT).show()
            }
        }
    }

    NoteListScreen(
        state = state, onAction = { action ->
            if (action is NoteListAction.OnClickNote) {
                onNavigateToAddNote(action.note.id)
            } else {
                viewModel.onAction(action)
            }
        }
    )

    DeleteConfirmation(isVisible = state.isDeleteDialogShown, onDismiss = {
        viewModel.onAction(NoteListAction.OnDismissDeletingNote)
    }, onConfirm = {
        viewModel.onAction(NoteListAction.OnConfirmDeletingNote)
    })
}

@Composable
fun NoteListScreen(
    state: NoteListState,
    onAction: (NoteListAction) -> Unit,
) {
    Scaffold(floatingActionButton = {
        AddNoteFloatingButton(
            onClick = {
                onAction(NoteListAction.OnClickAddNote)
            })
    }, topBar = {
        NoteListTopBar(
            tag = state.userTag
        )
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(innerPadding)
                .padding(
                    horizontal = 20.dp, vertical = 20.dp
                )
        ) {

            if (state.notes.isNotEmpty()) {
                ListView(state = state, onAction = onAction)
            }

            AnimatedVisibility(state.notes.isEmpty()) {
                EmptyNoteList()
            }
        }
    }

}

@Composable
private fun DeleteConfirmation(
    isVisible: Boolean, onDismiss: () -> Unit, onConfirm: () -> Unit
) {
    if (isVisible) BasicAlertDialog(
        onDismissRequest = onDismiss, properties = DialogProperties()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12))
                .background(MaterialTheme.colorScheme.background)
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Delete Note?",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Are you sure you want to delete this note?. This action cannot be undone.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 12.dp
                    ),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(
                    onClick = onConfirm
                ) {
                    Text(
                        text = "Delete",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center,


                    )
                }
                TextButton(
                    onClick = onDismiss
                ) {
                    Text(
                        text = "Cancel",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
        }
    }
}

@Composable
private fun ListView(
    state: NoteListState, onAction: (NoteListAction) -> Unit
) {
    val isDeviceType = getDeviceType()
    val count = remember(isDeviceType) {
        when (isDeviceType) {
            DeviceType.MOBILE_PORTRAIT -> 2
            DeviceType.TABLET_PORTRAIT -> 2
            DeviceType.LANDSCAPE -> 3
        }
    }
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(count),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalItemSpacing = 12.dp
    ) {
        items(
            items = state.notes, key = { it.id }) { note ->
            NoteItem(
                note = note, onAction = onAction, modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    NoteMarkTheme {
        NoteListScreen(
            state = NoteListState(
                userTag = "JS", notes = listOf(
                    Note(
                        id = "1",
                        title = "Note Title",
                        content = "Note Content",
                        createdAt = ZonedDateTime.now(),
                        lastEditedAt = ZonedDateTime.now()
                    ), Note(
                        id = "2",
                        title = "Note Title 2",
                        content = "Note Content 2 is the best note ever",
                        createdAt = ZonedDateTime.now().minusDays(1),
                        lastEditedAt = ZonedDateTime.now().minusDays(1)
                    )
                )
            ), onAction = {})
    }
}