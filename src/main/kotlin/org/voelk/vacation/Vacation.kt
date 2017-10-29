package org.voelk.vacation

import java.time.LocalDate
import java.time.temporal.ChronoUnit

class Vacation(start: LocalDate, end: LocalDate) {

    private val durationInDays = start.until(end.plusDays(1), ChronoUnit.DAYS)

    init {
        if (end.isBefore(start))
            throw IllegalArgumentException("Start must be before end")
        if (durationInDays > 1000)
            throw IllegalArgumentException("Maximum allowed period is 1000 days. Your period is $durationInDays days")
    }

    fun durationInDays(): Long {
        return durationInDays
    }
}