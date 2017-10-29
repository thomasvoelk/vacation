package org.voelk.vacation


import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.jupiter.api.Test
import java.time.LocalDate
import kotlin.test.assertFailsWith

class VacationTest {

    private var today = LocalDate.now()

    @Test
    fun sameDay_vacationIsOneDay() {
        MatcherAssert.assertThat(Vacation(today, today).durationInDays(), CoreMatchers.`is`(1L))
    }

    @Test
    fun nextDay_vacationIsTwoDays() {
        MatcherAssert.assertThat(Vacation(today, today.plusDays(1)).durationInDays(), CoreMatchers.`is`(2L))
    }

    @Test
    fun maxOf_1000_days_is_ok() {
        MatcherAssert.assertThat(Vacation(today, today.plusDays(999)).durationInDays(), CoreMatchers.`is`(1000L))
    }

    @Test()
    fun moreThan1000_notAllowed() {
        assertFailsWith(IllegalArgumentException::class, {
            Vacation(today, today.plusDays(1000)).durationInDays()
        })
    }

    @Test
    fun endMustNotBeBeforeStart() {
        assertFailsWith(IllegalArgumentException::class, {
            Vacation(today, today.minusDays(1)).durationInDays()
        })
    }


}