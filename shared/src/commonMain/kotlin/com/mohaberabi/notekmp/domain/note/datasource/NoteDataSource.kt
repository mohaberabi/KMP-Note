package com.mohaberabi.notekmp.domain.note.datasource

import com.mohaberabi.notekmp.domain.note.model.NoteModel


interface NoteDataSource {

    suspend fun insertNote(note: NoteModel)

    suspend fun getNoteById(id: Long): NoteModel?

    suspend fun getAllNotes(): List<NoteModel>
    suspend fun deleteNoteBuyId(id: Long)
}