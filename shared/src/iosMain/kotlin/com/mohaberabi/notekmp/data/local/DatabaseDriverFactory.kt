package com.mohaberabi.notekmp.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.mohaberabi.note.database.AppDatabase

actual class DatabaseDriverFactory {


    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(AppDatabase.Schema, "note.db")
    }
}