package com.example.breathingreport.utilities

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.toDate(pattern: String = "yyyy-MM-dd HH:mm:ss"): Date? {
    val sdFormat = try {
        SimpleDateFormat(pattern)
    } catch (e: IllegalArgumentException) {
        null
    }

    val date = sdFormat?.let {
        try {
            it.parse(this)
        } catch (e: ParseException) {
            null
        }
    }

    return date
}


fun Date.toString(pattern: String = "yyyy-MM-dd HH:mm:ss"): String? {
    val sdFormat = try {
        SimpleDateFormat(pattern)
    } catch (e: IllegalArgumentException) {
        null
    }

    val txt = sdFormat?.let {
        try {
            it.format(this)
        } catch (e: ParseException) {
            null
        }
    }

    return txt
}

