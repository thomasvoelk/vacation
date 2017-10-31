package org.voelk.vacation

import org.springframework.web.bind.annotation.*
import org.voelk.vacation.api.*
import org.voelk.vacation.domain.*
import java.time.*
import java.time.format.*
import java.util.stream.*

@RestController
class WorkingDaysController(private val workingDaysCalculator: WorkingDaysCalculator) {

    @GetMapping("/days/working")
    fun workingDays(@RequestParam("start") start: String, @RequestParam("end") end: String): WorkingDays {
        val workingPlace = WorkingPlace("by")
        return convert(workingDaysCalculator.workingDaysBetween(LocalDate.parse(start), LocalDate.parse(end), workingPlace))
    }

    private fun convert(days: Stream<LocalDate>): WorkingDays {
        val dates = days.map { d -> DateTimeFormatter.ISO_LOCAL_DATE.format(d) }.collect(Collectors.toSet()).sorted()
        return WorkingDays(dates, dates.size.toDouble())
    }
}