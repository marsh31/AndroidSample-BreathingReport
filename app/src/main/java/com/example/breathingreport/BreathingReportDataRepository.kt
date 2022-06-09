package com.example.breathingreport

import android.util.Log
import android.content.Context
import io.reactivex.Single
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BreathingReportDataRepository {
    companion object {
        lateinit var database: AppDatabase
    }

    init {
        val context = SingletonContext.applicationContext()
        database = AppDatabase.getInstance(context)
    }

    private fun sampleCodeUsingDB() {
        val dao = database.reportDao()

        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.Default) {
                val pre_data = dao.getAll()
                dao.insertAll(Report(pre_data.size, "2022-05-30", 20 + pre_data.size))
            }
        }
    }
}