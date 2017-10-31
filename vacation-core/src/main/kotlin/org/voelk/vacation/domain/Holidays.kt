package org.voelk.vacation.domain

import de.jollyday.HolidayManager
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.util.stream.Collectors

@Component
internal class Holidays {
    fun between(start: LocalDate, end: LocalDate, location: String): Set<LocalDate> {
        return HolidayManager.getInstance().getHolidays(start, end, location).stream().map { h -> h.date }.collect(Collectors.toSet())
    }

}