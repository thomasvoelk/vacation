package org.voelk.vacation.domain


import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class DaysTest {

    private var today = LocalDate.now()

    @Test
    fun sameDay_isOneDay() {
        assertThat(Days().between(today, today), `is`(setOf(today)))

    }

    @Test
    fun nextDay_isTwoDays() {
        val tomorrow = today.plusDays(1)
        assertThat(Days().between(today, tomorrow), `is`(setOf(today, tomorrow)))
    }

}