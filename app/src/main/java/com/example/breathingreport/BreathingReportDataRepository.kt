package com.example.breathingreport

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BreathingReportDataRepository @Inject constructor(private val reportDao: ReportDao) {
    suspend fun getAllReport() = reportDao.getAll()

    suspend fun registerBoltToday(bolt: Int) {
        val size = reportDao.getAll().size
        val data = Report(size + 1, Date(), bolt)
        reportDao.insertAll(data)
    }
}
