package org.voelk.vacation

import java.time.LocalDate
import java.time.temporal.ChronoUnit

class Vacation(private val start: LocalDate, private val end: LocalDate) {

    init {
        if (end.isBefore(start))
            throw IllegalArgumentException("Start must be before end")
        val durationInDays = durationInDays()
        if (durationInDays > 1000)
            throw IllegalArgumentException("Maximum allowed period is 1000 days. Your period is $durationInDays days")
    }

    fun durationInDays(): Long {
        return ChronoUnit.DAYS.between(start, end.plusDays(1))
    }
}