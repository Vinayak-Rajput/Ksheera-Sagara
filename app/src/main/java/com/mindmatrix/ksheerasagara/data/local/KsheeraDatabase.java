package com.mindmatrix.ksheerasagara.data.local;

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ksheerasagara.data.local.dao.DairyDao
import com.ksheerasagara.data.local.entity.CowProfile
import com.ksheerasagara.data.local.entity.ExpenseLog
import com.ksheerasagara.data.local.entity.MilkLog

/**
 * The main offline-first Database for Ksheera-Sagara.
 */
@Database(
        entities = [
        CowProfile::class,
        MilkLog::class,
        ExpenseLog::class
    ],
        version = 1,
        exportSchema = false
        )
        @TypeConverters(Converters::class)
        abstract class KsheeraDatabase : RoomDatabase() {

    abstract val dairyDao: DairyDao

    companion object {
        const val DATABASE_NAME = "ksheera_sagara_db"
    }
}
