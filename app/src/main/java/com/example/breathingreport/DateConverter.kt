package com.example.breathingreport

import androidx.room.TypeConverter
import java.util.*

class DateConverter {
    @TypeConverter
    fun longToDate(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToLong(date: Date?): Long? {
        return date?.let { it.time }
    }
}