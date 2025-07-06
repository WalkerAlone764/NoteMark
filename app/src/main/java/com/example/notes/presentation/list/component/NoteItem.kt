package com.example.notes.presentation.list.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.presentation.designsystem.theme.NoteMarkTheme
import com.example.core.presentation.util.DeviceType
import com.example.core.presentation.util.getDeviceType
import com.example.notes.domain.model.Note
import com.example.notes.presentation.list.NoteListAction
import java.time.LocalDate
import java.time.ZonedDateTime

@Composable
fun NoteItem(
    note: Note,
    onAction: (NoteListAction) -> Unit,
    modifier: Modifier = Modifier
) {

    val isDeviceType = getDeviceType()
    val wordLimit by animateIntAsState(
        targetValue = when(isDeviceType) {
            DeviceType.MOBILE_PORTRAIT -> 150
            DeviceType.TABLET_PORTRAIT -> 250
            DeviceType.LANDSCAPE -> 250
        },
    )
    val contentText by remember(note.content) {
        derivedStateOf {
            val actualWordLength = note.content.length
            if (actualWordLength > wordLimit) {
                note.content.take(wordLimit) + "..."
            } else {
                note.content
            }
        }
    }
    val formattedDate by remember(note.createdAt) {
        derivedStateOf {
            val convertedToLocalDate = note.createdAt.toLocalDate()
            val currentYear = LocalDate.now().year
            if (convertedToLocalDate.year == currentYear) {
                "${convertedToLocalDate.month} ${convertedToLocalDate.dayOfMonth}"
            } else {
                "${convertedToLocalDate.month} ${convertedToLocalDate.dayOfMonth}, ${convertedToLocalDate.year}"
            }
        }
    }

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12))
            .background(MaterialTheme.colorScheme.background)

            .combinedClickable(onLongClick = {
                onAction(NoteListAction.OnHoldNoteItem(note))
            }, onClick = {
                onAction(NoteListAction.OnClickNote(note))
            }
            )

            .padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            text = formattedDate,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = contentText,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }

}

@Preview
@Composable
fun NoteItemPreview() {
    val note = Note(
        id = "1",
        title = "Note Title",
        content = "Note Content",
        createdAt = ZonedDateTime.now(),
        lastEditedAt = ZonedDateTime.now()
    )
    NoteMarkTheme {
        NoteItem(note = note, onAction = {})
    }
}