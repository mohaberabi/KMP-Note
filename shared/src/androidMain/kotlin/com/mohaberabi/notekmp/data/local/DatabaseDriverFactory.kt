package com.mohaberabi.notekmp.data.local

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.mohaberabi.note.database.AppDatabase

actual class DatabaseDriverFactory(private val context: Context) {


    actual fun createDriver(): SqlDriver =
        AndroidSqliteDriver(AppDatabase.Schema, context, "note.db")


}