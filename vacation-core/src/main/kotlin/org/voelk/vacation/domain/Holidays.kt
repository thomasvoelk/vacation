package org.voelk.vacation.domain

import de.jollyday.*
import org.springframework.stereotype.*
import java.time.*
import java.util.stream.*

@Component
internal class Holidays {
    fun between(start: LocalDate, end: LocalDate, location: String): Set<LocalDate> {
        return HolidayManager.getInstance().getHolidays(start, end, location).stream().map { h -> h.date }.collect(Collectors.toSet())
    }

}