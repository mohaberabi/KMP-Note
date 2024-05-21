package com.mohaberabi.notekmp.data

import com.mohaberabi.note.database.AppDatabase
import com.mohaberabi.notekmp.domain.note.datasource.NoteDataSource
import com.mohaberabi.notekmp.domain.note.model.NoteModel
import com.mohaberabi.notekmp.domain.time.DateTimeUtil

class SqlDelightNoteDataSource(
    private val database: AppDatabase,
) : NoteDataSource {

    private val queries = database.appDatabaseQueries
    override suspend fun insertNote(note: NoteModel) = queries.insertNote(
        id = note.id,
        title = note.title,
        content = note.content,
        colorHex = note.colorHex,
        created = DateTimeUtil.toEpochMillis(note.created)
    )

    override suspend fun getNoteById(id: Long): NoteModel? =
        queries.getNotById(id).executeAsOneOrNull()?.toNote()

    override suspend fun getAllNotes(): List<NoteModel> = queries
        .getAllNotes()
        .executeAsList().map { it.toNote() }

    override suspend fun deleteNoteBuyId(id: Long) = queries.deleteNoteById(id)
}