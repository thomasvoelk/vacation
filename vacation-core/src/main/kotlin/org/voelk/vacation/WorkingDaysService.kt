package org.voelk.vacation

import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class WorkingDaysService {
    fun workingDaysIn(start: LocalDate, end: LocalDate): Double {
        return Vacation(start, end).durationInDays()
    }
}