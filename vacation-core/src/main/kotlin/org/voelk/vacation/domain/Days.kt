package org.voelk.vacation.domain

import org.springframework.stereotype.*
import java.time.*
import java.time.temporal.*
import java.util.stream.*
import java.util.stream.Stream.*

@Component
internal class Days {

    fun between(start: LocalDate, end: LocalDate): Set<LocalDate> {
        return iterate(start, { d -> d.plusDays(1) })
                .limit(ChronoUnit.DAYS.between(start, end.plusDays(1)))
                .collect(Collectors.toSet())
    }
}