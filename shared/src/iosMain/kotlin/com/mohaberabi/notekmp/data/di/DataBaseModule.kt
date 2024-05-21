package com.mohaberabi.notekmp.data.di

import com.mohaberabi.note.database.AppDatabase
import com.mohaberabi.notekmp.data.SqlDelightNoteDataSource
import com.mohaberabi.notekmp.data.local.DatabaseDriverFactory
import com.mohaberabi.notekmp.domain.note.datasource.NoteDataSource

class DataBaseModule {

    val factory by lazy { DatabaseDriverFactory() }


    val noteDataSource: NoteDataSource by lazy {
        SqlDelightNoteDataSource(AppDatabase(factory.createDriver()))
    }
}