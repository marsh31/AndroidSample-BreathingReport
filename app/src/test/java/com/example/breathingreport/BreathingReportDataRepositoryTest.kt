package com.example.breathingreport
/* NAME:   BreathingReportDataRepository.kt
 * AUTHOR: marsh
 *
 * NOTE:
 *   mockitoでは、suspendの関数に対して、ArgumentCaptorで引数チェックが難しかった。
 * それに対して、mockkは、比較的かんたんにテストを書くことができた。
 * 書き方についても大きく変更がないことから、mockkを使用している。
 */

import com.example.breathingreport.utilities.toDate
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.*
import java.util.*

class BreathingReportDataRepositoryTest {
    private val reportA = Report(1, "2022-01-01".toDate("yyyy-MM-dd")!!, 20)
    private val reportB = Report(2, "2022-01-02".toDate("yyyy-MM-dd")!!, 22)
    private val reportC = Report(3, "2022-01-03".toDate("yyyy-MM-dd")!!, 21)
    private val testReports = listOf(reportA, reportB, reportC)

    val slot = slot<Report>()

    val dao = mockk<ReportDao>() {
        coEvery { getAll() } returns testReports
    }

    @Mock
    val date = mockk<Date>()

    @InjectMockKs
    var repository: BreathingReportDataRepository = BreathingReportDataRepository(dao)

    @Test
    fun getAllReportTest() {
        runBlocking {
            val reports = repository.getAllReport()

            Assert.assertEquals(reportA, reports[0])
            Assert.assertEquals(reportB, reports[1])
            Assert.assertEquals(reportC, reports[2])
        }
    }

    @Test
    fun registerBoltTodayTest() {
        val bolt = 30
        val report = Report(testReports.size + 1, Date(), bolt)

        coEvery {
            dao.insertAll(capture(slot))
        } coAnswers {
            assert(! slot.isNull)
            Assert.assertEquals(report.uid, slot.captured.uid)
            Assert.assertEquals(report.bolt, slot.captured.bolt)
            Assert.assertEquals(report.date.toString(), slot.captured.date.toString())
        }

        runBlocking {
            repository.registerBoltToday(bolt)
        }
    }
}