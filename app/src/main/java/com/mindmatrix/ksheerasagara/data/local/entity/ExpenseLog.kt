package com.mindmatrix.ksheerasagara.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Represents an operational expense log for the dairy farm.
 */
@Entity(
    tableName = "expense_logs",
    foreignKeys = [
        ForeignKey(
            entity = CowProfile::class,
            parentColumns = ["id"],
            childColumns = ["cowId"],
            onDelete = ForeignKey.SET_NULL
        )
    ]
)
data class ExpenseLog(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    // Nullable: Expense could be for a specific cow (ex. Medical) or the whole farm (ex. Labor)
    @ColumnInfo(index = true)
    val cowId: Long?,

    val category: ExpenseCategory,

    val amount: Double,

    val note: String = "",

    // Unix timestamp
    val loggedDate: Long = System.currentTimeMillis()
)
