package org.voelk.vacation

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
class WorkingDaysController(private val workingDaysService: WorkingDaysService) {

    @GetMapping("/days/working")
    fun workingDays(@RequestParam("start") start:String, @RequestParam("end") end:String): Double {
        return workingDaysService.workingDaysIn(LocalDate.parse(start), LocalDate.parse(end))
    }
}