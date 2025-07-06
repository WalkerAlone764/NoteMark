package com.example.notes.data.mapper

import com.example.notes.data.dto.NoteDto
import com.example.notes.data.entity.NoteEntity
import com.example.notes.domain.model.Note
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

fun Note.toNoteDto(): NoteDto {
    return NoteDto(
        id = id,
        title = title,
        content = content,
        createdAt = createdAt.withZoneSameInstant(ZoneId.of("UTC")).toInstant().toString(),
        lastEditedAt = lastEditedAt.withZoneSameInstant(ZoneId.of("UTC")).toInstant().toString()
    )
}

fun NoteDto.toNote(): Note {
    return Note(
        id = id,
        title = title,
        content = content,
        createdAt = Instant.parse(createdAt).atZone(ZoneId.systemDefault()),
        lastEditedAt = Instant.parse(lastEditedAt).atZone(ZoneId.systemDefault())
    )
}

fun Note.toNoteEntity(): NoteEntity {
    return NoteEntity(
        id = id,
        title = title,
        content = content,
        createdAt = createdAt.withZoneSameInstant(ZoneId.of("UTC")).toString(),
        lastEditedAt = lastEditedAt.withZoneSameInstant(ZoneId.of("UTC")).toString()
    )
}

fun NoteEntity.toNote(): Note {
    return Note(
        id = id,
        title = title,
        content = content,
        createdAt = ZonedDateTime.parse(createdAt).withZoneSameInstant(ZoneId.systemDefault()),
        lastEditedAt = ZonedDateTime.parse(lastEditedAt).withZoneSameInstant(ZoneId.systemDefault())
    )
}