package com.example.breathingreport

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Report(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "bolt") val bold: Int,
)
