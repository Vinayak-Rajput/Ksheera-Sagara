package com.mindmatrix.ksheerasagara.data.local

import androidx.room.TypeConverter
import com.ksheerasagara.data.local.entity.ExpenseCategory

/**
 * Type converters for Room to support Enums natively.
 */
class Converters {
    @TypeConverter
    fun fromExpenseCategory(category: ExpenseCategory): String {
        return category.name
    }

    @TypeConverter
    fun toExpenseCategory(name: String): ExpenseCategory {
        return try {
            ExpenseCategory.valueOf(name)
        } catch (e: IllegalArgumentException) {
            ExpenseCategory.OTHER
        }
    }
}
