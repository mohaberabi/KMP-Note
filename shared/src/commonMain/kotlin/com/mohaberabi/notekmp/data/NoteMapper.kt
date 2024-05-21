package com.mohaberabi.notekmp.data

import com.mohaberabi.note.database.NoteEntity
import com.mohaberabi.notekmp.domain.note.model.NoteModel
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime


fun NoteEntity.toNote(): NoteModel {
    return NoteModel(
        id = id,
        title = title,
        content = content,
        created = Instant.fromEpochMilliseconds(created)
            .toLocalDateTime(TimeZone.currentSystemDefault()),
        colorHex = colorHex,
    )

}