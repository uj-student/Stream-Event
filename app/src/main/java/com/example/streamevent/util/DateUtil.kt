package com.example.streamevent.util

import com.ibm.icu.text.RuleBasedNumberFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.DAY_OF_MONTH
import kotlin.math.absoluteValue

private const val datePattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
private const val yearPattern = "yyyy"
private const val monthPattern = "MM"
private const val dayPattern = "dd"
private const val timePattern = "kk:mm" //24 hour

fun String.formatDate(): String = with(SimpleDateFormat(datePattern, Locale.getDefault()).parse(this) ?: Date()) {
    "${getDayName(this)}, ${this.time()}"
}

fun Date.getDisplayDate(): String = "${this.day()}.${this.month()}.${this.year()}"

fun Date.year(): String = SimpleDateFormat(yearPattern, Locale.getDefault()).format(this)

fun Date.month(): String = SimpleDateFormat(monthPattern, Locale.getDefault()).format(this)

fun Date.day(): String = SimpleDateFormat(dayPattern, Locale.getDefault()).format(this)

fun Date.time(): String = SimpleDateFormat(timePattern, Locale.getDefault()).format(this)

fun Date.isCurrentMonth(): Boolean = (Calendar.getInstance().get(Calendar.MONTH) + 1).toString() == this.month().trimStart { it == '0' }

fun Date.isToday(): Boolean = Calendar.getInstance().get(DAY_OF_MONTH).toString() == this.day().trimStart { it == '0' }

fun Date.isOneDayDifference(): Boolean = getNumberOfDays(this) == 1

fun Date.isYesterday(): Boolean = Calendar.getInstance().get(DAY_OF_MONTH) > this.day().trimStart { it == '0' }.toInt()

fun Date.isTomorrow(): Boolean = Calendar.getInstance().get(DAY_OF_MONTH) < this.day().trimStart { it == '0' }.toInt()

fun getNumberOfDays(date: Date): Int = (Calendar.getInstance().get(DAY_OF_MONTH) - date.day().trimStart { it == '0' }.toInt()).absoluteValue

fun getDayName(date: Date): String {
    if (date.isCurrentMonth()) when {
        date.isToday() -> {
            return "Today"
        }
        date.isOneDayDifference() -> {
            if (date.isYesterday()) {
                return "Yesterday"
            }
            if (date.isTomorrow()) {
                return "Tomorrow"
            }
        }
    }
    return if (getNumberOfDays(date) < 6) getDurationTillFunction(date) else date.getDisplayDate()
}

//assumption is day difference is greater than 1
fun getDurationTillFunction(date: Date): String = "In ${convertIntoWords(getNumberOfDays(date).toDouble())} days"

private fun convertIntoWords(number: Double): String? = RuleBasedNumberFormat(Locale.getDefault(), RuleBasedNumberFormat.SPELLOUT).format(number)
