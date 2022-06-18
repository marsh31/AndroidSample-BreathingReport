package com.example.breathingreport

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "report")
data class Report(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "date") val date: Date,
    @ColumnInfo(name = "bolt") val bolt: Int,
)
