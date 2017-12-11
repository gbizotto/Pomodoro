package br.gbizotto.pomodoro.utils

import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar

/**
 * Created by Gabriela on 11/12/2017.
 */

object DateUtils {

    fun isSameDate(first: Date, second: Date): Boolean {
        val cal1 = GregorianCalendar()
        cal1.time = first
        val cal2 = GregorianCalendar()
        cal2.time = second

        return isSameDay(cal1, cal2) && isSameMonth(cal1, cal2) && isSameYear(cal1, cal2)
    }

    fun isToday(date: Date?) : Boolean {
        var calendar = GregorianCalendar()
        calendar.time = date
        var today = GregorianCalendar()
        return isSameDay(calendar, today) && isSameMonth(calendar, today) && isSameYear(calendar, today)
    }

    fun isYesterday(date: Date?) : Boolean {
        var calendar = GregorianCalendar()
        calendar.time = date
        var today = GregorianCalendar()
        today.add(Calendar.DAY_OF_MONTH, -1)
        return isSameDay(calendar, today) && isSameMonth(calendar, today) && isSameYear(calendar, today)
    }

    fun getMinutes(milliseconds: Long?): String {
        if (milliseconds == null) {
            return "00"
        }
        var seconds = milliseconds / 1000
        var minutes = (seconds / 60).toInt()
        return format(minutes)
    }

    fun getSeconds(milliseconds: Long?) : String {
        if (milliseconds == null) {
            return "00"
        }
        var seconds = milliseconds / 1000
        return format(((seconds % 60)).toInt())
    }

    private fun format(time: Int) : String {
        if (time>= 10) {
            return time.toString()
        }
        return "0$time"
    }

    private fun isSameDay(first: Calendar, second: Calendar): Boolean {
        return first.get(Calendar.DAY_OF_MONTH) == second.get(Calendar.DAY_OF_MONTH)
    }

    private fun isSameMonth(first: Calendar, second: Calendar): Boolean {
        return first.get(Calendar.MONTH) == second.get(Calendar.MONTH)
    }

    private fun isSameYear(first: Calendar, second: Calendar): Boolean {
        return first.get(Calendar.YEAR) == second.get(Calendar.YEAR)
    }
}
