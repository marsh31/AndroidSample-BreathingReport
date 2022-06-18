package com.example.breathingreport

import com.example.breathingreport.utilities.toDate
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun dateTest() {
        val dateA = "2020-02-04".toDate("yyyy-MM-dd")!!.time
        val dateB = "2020-02-05".toDate("yyyy-MM-dd")!!.time
        val dateB2 = "2020-02-06".toDate("yyyy-MM-dd")!!.time

        val dateC = dateB - dateA
        val dateD = dateB2 - dateA

        System.out.println("Long time: ${dateC}")
        System.out.println("Long time: ${dateD}")
    }
}