package org.voelk.vacation

import java.time.DayOfWeek
import java.time.LocalDate

class Weekends(val start: LocalDate, val end: LocalDate) {
    fun days(): Set<LocalDate> {
        val days = mutableSetOf<LocalDate>()
        var day = start
        while(day.isEqual(end) || day.isBefore(end)) {
            if (isWeekend(day))
                days.add(day)
            day = day.plusDays(1)
        }
        return days
    }

    private fun isWeekend(day: LocalDate) = setOf(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY).contains(day.dayOfWeek)

}