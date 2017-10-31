package org.voelk.vacation

import org.springframework.web.bind.annotation.*
import org.voelk.vacation.api.*
import org.voelk.vacation.domain.*
import java.time.*

@RestController
class WorkingDaysController(private val workingDaysCalculator: WorkingDaysCalculator) {

    @GetMapping("/days/working")
    fun workingDays(@RequestParam("start") start: String, @RequestParam("end") end: String): WorkingDays {
        val workingPlace = WorkingPlace("by")
        return WorkingDays(workingDaysCalculator.workingDaysBetween(LocalDate.parse(start), LocalDate.parse(end), workingPlace))
    }
}