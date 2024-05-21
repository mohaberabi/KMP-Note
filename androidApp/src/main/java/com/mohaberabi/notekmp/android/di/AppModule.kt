package com.mohaberabi.notekmp.android.di

import com.mohaberabi.note.database.AppDatabase
import com.mohaberabi.notekmp.android.details.viewmodel.DetailViewModel
import com.mohaberabi.notekmp.android.note.viewmodel.NoteViewModel
import com.mohaberabi.notekmp.data.SqlDelightNoteDataSource
import com.mohaberabi.notekmp.data.local.DatabaseDriverFactory
import com.mohaberabi.notekmp.domain.note.datasource.NoteDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {


    single { AppDatabase(get()) }
    single { DatabaseDriverFactory(get()).createDriver() }
    single<NoteDataSource> { SqlDelightNoteDataSource(get()) }
    viewModel { DetailViewModel(get(), get()) }


    viewModel { NoteViewModel(get(), get()) }
}