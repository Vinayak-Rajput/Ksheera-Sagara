package com.mindmatrix.ksheerasagara.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Represents a milk collection log (Income source).
 */
@Entity(
    tableName = "milk_logs",
    foreignKeys = [
        ForeignKey(
            entity = CowProfile::class,
            parentColumns = ["id"],
            childColumns = ["cowId"],
            onDelete = ForeignKey.SET_NULL // Keep records for financial accuracy if cow trace is lost/deleted
        )
    ]
)
data class MilkLog(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    // Nullable to allow bulk entries without a specific cow
    @ColumnInfo(index = true)
    val cowId: Long?,

    val liters: Double,

    val fatPercentage: Double,

    // Unix timestamp for tracking the date of collection
    val loggedDate: Long = System.currentTimeMillis()
)
