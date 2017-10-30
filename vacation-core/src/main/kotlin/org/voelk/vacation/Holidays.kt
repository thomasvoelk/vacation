package org.voelk.vacation

import de.jollyday.HolidayManager
import java.time.LocalDate
import java.util.stream.Collectors

class Holidays(val start: LocalDate, val end: LocalDate, val location: String) {
    fun days(): Set<LocalDate> {
        return HolidayManager.getInstance().getHolidays(start, end, location).stream().map { h -> h.date }.collect(Collectors.toSet())
    }

}