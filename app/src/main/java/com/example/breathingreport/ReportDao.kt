package com.example.breathingreport

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ReportDao {
    @Query("SELECT * FROM report")
    suspend fun getAll(): List<Report>

    @Query("SELECT * FROM report WHERE uid IN (:reportIds)")
    suspend fun loadAllByIds(reportIds: IntArray): List<Report>

    @Query("SELECT * FROM report WHERE date LIKE :date")
    suspend fun findByDate(date: String): Report

    @Insert
    suspend fun insertAll(vararg reports: Report)

    @Delete
    suspend fun delete(report: Report)
}