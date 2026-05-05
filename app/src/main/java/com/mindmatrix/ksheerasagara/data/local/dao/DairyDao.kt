package com.mindmatrix.ksheerasagara.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ksheerasagara.data.local.entity.CowProfile
import com.ksheerasagara.data.local.entity.ExpenseLog
import com.ksheerasagara.data.local.entity.MilkLog
import kotlinx.coroutines.flow.Flow

/**
 * Unified Data Access Object for Ksheera-Sagara.
 * Handles CRUD operations and analytical queries required for the Dashboard.
 */
@Dao
interface DairyDao {

    // ========================
    // Cow Profiles
    // ========================
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCow(cow: CowProfile): Long

    @Query("SELECT * FROM cow_profiles ORDER BY nameOrTag ASC")
    fun getAllCows(): Flow<List<CowProfile>>

    // ========================
    // Milk Logs (Income)
    // ========================
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMilkLog(milkLog: MilkLog): Long

    @Query("SELECT * FROM milk_logs ORDER BY loggedDate DESC")
    fun getAllMilkLogs(): Flow<List<MilkLog>>

    // Flow that emits total liters for a given daily/monthly timestamp range
    @Query("SELECT COALESCE(SUM(liters), 0.0) FROM milk_logs WHERE loggedDate BETWEEN :startDate AND :endDate")
    fun getTotalLitersFlow(startDate: Long, endDate: Long): Flow<Double>

    // Assuming we calculate exact income later in ViewModel depending on fat% logic,
    // but if we needed raw entries for profit calculation:
    @Query("SELECT * FROM milk_logs WHERE loggedDate BETWEEN :startDate AND :endDate")
    fun getMilkLogsInRange(startDate: Long, endDate: Long): Flow<List<MilkLog>>

    // ========================
    // Expense Logs
    // ========================
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpenseLog(expenseLog: ExpenseLog): Long

    @Query("SELECT * FROM expense_logs ORDER BY loggedDate DESC")
    fun getAllExpenseLogs(): Flow<List<ExpenseLog>>

    // Flow that emits total expenses for a daily/monthly dashboard View
    @Query("SELECT COALESCE(SUM(amount), 0.0) FROM expense_logs WHERE loggedDate BETWEEN :startDate AND :endDate")
    fun getTotalExpensesFlow(startDate: Long, endDate: Long): Flow<Double>

    // For Pie Chart groupings
    @Query("SELECT * FROM expense_logs WHERE loggedDate BETWEEN :startDate AND :endDate")
    fun getExpenseLogsInRange(startDate: Long, endDate: Long): Flow<List<ExpenseLog>>
}
