package com.example.breathingreport

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.breathingreport.utilities.toDate
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
class ReportDaoTest {
    private lateinit var target: ReportDao
    private lateinit var database: AppDatabase

    private val reportA = Report(1, "2022-01-01".toDate("yyyy-MM-dd")!!, 20)
    private val reportB = Report(2, "2022-01-02".toDate("yyyy-MM-dd")!!, 22)
    private val reportC = Report(3, "2022-01-03".toDate("yyyy-MM-dd")!!, 21)

    @Before
    fun setup() = runBlocking {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        target = database.reportDao()

        // Insert test data.
        target.insertAll(reportB, reportA, reportC)
    }

    @After
    fun testDown() {
        database.close()
    }

    @Test
    fun getAllTest() = runBlocking {
        val reports = target.getAll()

        assert(reportA == reports[0])
        assert(reportB == reports[1])
        assert(reportC == reports[2])
    }

    @Test
    fun loadAllByIdsTest() = runBlocking {
        val reports = target.loadAllByIds(intArrayOf(1, 3))
        assert(reportA == reports[0])
        assert(reportC == reports[1])
    }

    @Test
    fun insertAllTest() = runBlocking {
        val report: Report = Report(0, "2020-02-04".toDate("yyyy-MM-dd")!!, 20)
        target.insertAll(report)

        val reports = target.getAll()
        assert(report == reports[0])
        assert(reportA == reports[1])
        assert(reportB == reports[2])
        assert(reportC == reports[3])
    }

    @Test
    fun deleteTest() = runBlocking {
        target.delete(reportA)

        val reports = target.getAll()
        assert(reportB == reports[0])
        assert(reportC == reports[1])
    }
}