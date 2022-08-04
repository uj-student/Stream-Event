package com.example.streamevent.util

import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.DAY_OF_MONTH
import kotlin.math.absoluteValue

private const val datePattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
private const val yearPattern = "yyyy"
private const val monthPattern = "MM"
private const val dayPattern = "dd"
private const val timePattern = "kk:mm" //24 hour

fun String.formatDate(): String {
    val dateValue = SimpleDateFormat(datePattern, Locale.getDefault()).parse(this)
    return "${getDayName(dateValue?: Date())}, ${dateValue?.time()} "
}

fun Date.getDisplayDate(): String = "${this.day()}.${this.month()}.${this.year()}"

fun Date.year(): String = SimpleDateFormat(yearPattern, Locale.getDefault()).format(this)

fun Date.month(): String = SimpleDateFormat(monthPattern, Locale.getDefault()).format(this)

fun Date.day(): String = SimpleDateFormat(dayPattern, Locale.getDefault()).format(this)

fun Date.time(): String = SimpleDateFormat(timePattern, Locale.getDefault()).format(this)

fun getDayName(date: Date): String {
    if ((Calendar.getInstance().get(Calendar.MONTH) + 1).toString() == date.month().trimStart { it == '0' }) {
        if (Calendar.getInstance().get(DAY_OF_MONTH).toString() == date.day().trimStart { it == '0' }) {
            return "Today"
        }
        if ((Calendar.getInstance().get(DAY_OF_MONTH) - date.day().trimStart { it == '0' }.toInt()).absoluteValue == 1) {
            if (Calendar.getInstance().get(DAY_OF_MONTH) > date.day().trimStart { it == '0' }.toInt()) {
                return "Yesterday"
            }
            if (Calendar.getInstance().get(DAY_OF_MONTH) < date.day().trimStart { it == '0' }.toInt()) {
                return "Tomorrow"
            }
        }
    }
    return date.getDisplayDate()
}

