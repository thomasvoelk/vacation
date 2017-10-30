package org.voelk.vacation

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
class WorkingDaysController(private val workingDaysService: WorkingDaysService) {

    @GetMapping("/working-days")
    fun workingDays(@RequestParam("start") start:String, @RequestParam("end") end:String): Int {
        return workingDaysService.workingDaysIn(LocalDate.parse(start), LocalDate.parse(end))
    }
}