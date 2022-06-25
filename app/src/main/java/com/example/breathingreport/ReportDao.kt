package com.example.breathingreport
/* NOTE:
 * Test&Debug:
 * https://developer.android.com/training/data-storage/room/testing-db?hl=ja
 */


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ReportDao {
    @Query("SELECT * FROM report")
    fun getAll(): List<Report>

    @Query("SELECT * FROM report WHERE uid IN (:reportIds)")
    fun loadAllByIds(reportIds: IntArray): List<Report>

    @Insert
    fun insertAll(vararg reports: Report)

    @Delete
    fun delete(report: Report)
}