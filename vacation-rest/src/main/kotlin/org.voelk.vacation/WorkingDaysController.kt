package org.voelk.vacation

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.voelk.vacation.api.WorkingDaysCalculator
import org.voelk.vacation.domain.WorkingPlace
import java.time.LocalDate

@RestController
class WorkingDaysController(private val workingDaysCalculator: WorkingDaysCalculator) {

    @GetMapping("/days/working")
    fun workingDays(@RequestParam("start") start: String, @RequestParam("end") end: String): Double {
        val workingPlace = WorkingPlace("by")
        return workingDaysCalculator.workingDaysBetween(LocalDate.parse(start), LocalDate.parse(end), workingPlace)
    }
}