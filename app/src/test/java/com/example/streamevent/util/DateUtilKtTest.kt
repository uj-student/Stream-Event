package com.example.streamevent.util

import org.junit.Assert.*
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.DATE

class DateUtilShould {

    private val currentDumpyDateString = "2022-07-06T10:30:42.719Z"
    private val dateSample: Date = SimpleDateFormat(getDatePattern(), Locale.getDefault()).parse(currentDumpyDateString) ?: Date()
    private val currentDateSample: Calendar = Calendar.getInstance()

    @Test
    fun formatDate() {
    }

    @Test
    fun getNumberOfDays() {
    }

    @Test
    fun getDayName() {
    }

    @Test
    fun getDurationTillFunction() {

    }

    @Test
    fun getDisplayDate() {
        assertEquals("06.07.2022, 10:30", currentDumpyDateString.formatDate())
    }

    @Test
    fun shouldReturnYear() {
        assertEquals("2022", dateSample.year())
    }

    @Test
    fun shouldReturnMonth() {
        assertEquals("07", dateSample.month())
    }

    @Test
    fun shouldReturnDay() {
        assertEquals("06", dateSample.day())
    }

    @Test
    fun shouldReturnTime() {
        assertEquals("10:30", dateSample.time())
    }

    @Test
    fun isCurrentMonth() {
        assertFalse(dateSample.isCurrentMonth())
        assertTrue(currentDateSample.time.isCurrentMonth())
    }

    @Test
    fun isToday() {
        assertFalse(dateSample.isToday())
        assertTrue(currentDateSample.time.isToday())
    }

    @Test
    fun isOneDayDifference() {
        assertFalse(dateSample.isOneDayDifference())
    }

    @Test
    fun isYesterday() {
        currentDateSample.apply {
            add(DATE, -1)
            assertTrue(time.isYesterday())
        }
        assertFalse(currentDateSample.time.isYesterday())
    }

    @Test
    fun isTomorrow() {
        assertFalse(currentDateSample.time.isTomorrow())
        currentDateSample.apply {
            add(DATE, 1)
            assertTrue(time.isTomorrow())
        }
    }
}