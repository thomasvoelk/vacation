package org.voelk.vacation.domain

import org.springframework.stereotype.Service
import org.voelk.vacation.api.WorkingDaysCalculator
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@Service
internal class WorkingDaysService : WorkingDaysCalculator {
    override fun workingDaysBetween(start: LocalDate, end: LocalDate, workingPlace: WorkingPlace): Double {
        assertValid(end, start)

        val nonWorkingDays = setOf(Holidays().between(start, end, workingPlace.location), Weekends().between(start, end))
        return (Days().between(start, end).size - nonWorkingDays.size).toDouble()
    }

    private fun assertValid(end: LocalDate, start: LocalDate) {
        if (end.isBefore(start))
            throw IllegalArgumentException("Start must be before end")
        if (ChronoUnit.DAYS.between(start, end.plusDays(1)) > 1000)
            throw IllegalArgumentException("Maximum allowed period is 1000 days. Your period is ${ChronoUnit.DAYS.between(start, end.plusDays(1)).toDouble()} days")
    }

}