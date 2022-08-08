package com.example.streamevent.util

import org.junit.Assert.*
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.DATE
import kotlin.math.absoluteValue

class DateUtilShould {

    private val currentDumpyDateString = "2022-07-06T10:30:42.719Z"
    private val dateSample: Date = SimpleDateFormat(getDatePattern(), Locale.getDefault()).parse(currentDumpyDateString) ?: Date()
    private val todayDateSample: Date = Calendar.getInstance().time
    private val yesterdayDateSample: Date = Calendar.getInstance().apply { add(DATE, -1) }.time
    private val tomorrowDateSample: Date = Calendar.getInstance().apply { add(DATE, 1) }.time

    @Test
    fun formatDate() {
        println(currentDumpyDateString.formatDate())
        println(Calendar.getInstance().apply { add(DATE, 200) }.time)
        assertEquals("06.07.2022, 10:30", currentDumpyDateString.formatDate())
    }

    @Test
    fun getNumberOfDays() {
        println((-137).absoluteValue)
        assertEquals(1, getNumberOfDays(tomorrowDateSample))
        assertEquals(0, getNumberOfDays(todayDateSample))
        assertEquals(1, getNumberOfDays(yesterdayDateSample))
        assertEquals(7, getNumberOfDays(Calendar.getInstance().apply { add(DATE, -7) }.time))
        assertEquals(15, getNumberOfDays(Calendar.getInstance().apply { add(DATE, 15) }.time))
        //see line 51
        assertEquals(1, getNumberOfDays(Calendar.getInstance().apply { add(DATE, 30) }.time))
    }

    @Test
    fun getDayName() {
        assertEquals("Yesterday", getDayName(yesterdayDateSample))
        assertEquals("Today", getDayName(todayDateSample))
        assertEquals("Tomorrow", getDayName(tomorrowDateSample))
    }

    @Test
    fun getDurationTillFunction() {
        assertEquals("In one days", getDurationTillFunction(tomorrowDateSample))
        assertEquals("In zero days", getDurationTillFunction(todayDateSample))
        assertEquals("In one days", getDurationTillFunction(yesterdayDateSample))
        assertEquals("In seven days", getDurationTillFunction(Calendar.getInstance().apply { add(DATE, 7) }.time))
        assertEquals("In fifteen days", getDurationTillFunction(Calendar.getInstance().apply { add(DATE, 15) }.time))
        // yes fifteen is correct, assumes that day difference doesn't span longer than a month
        assertEquals("In fifteen days", getDurationTillFunction(Calendar.getInstance().apply { add(DATE, 137) }.time))
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
        assertTrue(todayDateSample.isCurrentMonth())
    }

    @Test
    fun isToday() {
        assertFalse(dateSample.isToday())
        assertTrue(todayDateSample.isToday())
    }

    @Test
    fun isOneDayDifference() {
        assertFalse(dateSample.isOneDayDifference())
    }

    @Test
    fun isYesterday() {
        assertFalse(todayDateSample.isYesterday())
        assertTrue(yesterdayDateSample.isYesterday())
    }

    @Test
    fun isTomorrow() {
        assertFalse(todayDateSample.isTomorrow())
        assertTrue(tomorrowDateSample.isTomorrow())
    }
}