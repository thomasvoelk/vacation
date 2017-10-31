package org.voelk.vacation.domain

import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import org.voelk.vacation.api.WorkingDaysCalculator
import java.time.LocalDate
import kotlin.test.assertFailsWith

class WorkingDaysServiceTest {

    private val workingPlace = WorkingPlace("by")
    private val today = LocalDate.now()


    @Test
    fun maxOf_1000_days_is_ok() {
        val muchLater = today.plusDays(WorkingDaysCalculator.MAX_RANGE - 1L)
        //result doesn't really matter, what's important is that no exception is thrown
        assertThat(WorkingDaysService().workingDaysBetween(today, muchLater, workingPlace), org.hamcrest.CoreMatchers.not(0.0))
    }

    @Test()
    fun moreThan1000_notAllowed() {
        assertFailsWith(IllegalArgumentException::class, {
            WorkingDaysService().workingDaysBetween(today, today.plusDays(WorkingDaysCalculator.MAX_RANGE.toLong()), workingPlace)
        })
    }

    @Test
    fun endMustNotBeBeforeStart() {
        assertFailsWith(IllegalArgumentException::class, {
            WorkingDaysService().workingDaysBetween(today, today.minusDays(1), workingPlace)
        })
    }

}