package org.voelk.vacation.domain


import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.*
import org.junit.jupiter.api.*
import java.time.*

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