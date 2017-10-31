package org.voelk.vacation.domain

import org.springframework.stereotype.*
import java.time.*

@Component
internal class Weekends {
    fun between(start: LocalDate, end: LocalDate): Set<LocalDate> {
        val days = mutableSetOf<LocalDate>()
        var day = start
        while (day.isEqual(end) || day.isBefore(end)) {
            if (isWeekend(day))
                days.add(day)
            day = day.plusDays(1)
        }
        return days
    }

    private fun isWeekend(day: LocalDate) = setOf(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY).contains(day.dayOfWeek)

}