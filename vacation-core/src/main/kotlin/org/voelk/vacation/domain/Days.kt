package org.voelk.vacation.domain

import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.stream.Collectors
import java.util.stream.Stream.iterate

internal class Days {

    fun between(start: LocalDate, end: LocalDate): Set<LocalDate> {
        return iterate(start, { d -> d.plusDays(1) })
                .limit(ChronoUnit.DAYS.between(start, end.plusDays(1)))
                .collect(Collectors.toSet())
    }
}