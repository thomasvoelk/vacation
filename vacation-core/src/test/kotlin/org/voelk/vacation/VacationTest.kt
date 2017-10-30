package org.voelk.vacation


import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import kotlin.test.assertFailsWith

class VacationTest {

    private var today = LocalDate.now()

    @Test
    fun sameDay_vacationIsOneDay() {
        assertThat(Vacation(today, today).durationInDays(), `is`(1L))
    }

    @Test
    fun nextDay_vacationIsTwoDays() {
        assertThat(Vacation(today, today.plusDays(1)).durationInDays(), `is`(2L))
    }

    @Test
    fun maxOf_1000_days_is_ok() {
        assertThat(Vacation(today, today.plusDays(999)).durationInDays(), `is`(1000L))
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