package org.voelk.vacation.api

import org.voelk.vacation.domain.WorkingPlace
import java.time.LocalDate

interface WorkingDaysCalculator {
    companion object {
        const val MAX_RANGE = 1000
    }

    fun workingDaysBetween(start: LocalDate, end: LocalDate, workingPlace: WorkingPlace): Double
}