package org.voelk.vacation.api

import org.voelk.vacation.domain.*
import java.time.*
import java.util.stream.*

interface WorkingDaysCalculator {
    companion object {
        const val MAX_RANGE = 1000
    }


    fun workingDaysBetween(start: LocalDate, end: LocalDate, workingPlace: WorkingPlace): Stream<LocalDate>
}