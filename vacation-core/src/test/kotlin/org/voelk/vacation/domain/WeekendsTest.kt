package org.voelk.vacation.domain

import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.*
import org.junit.jupiter.api.*
import java.time.*

class WeekendsTest {
    @Test
    fun noWeekends() {
        val monday = LocalDate.parse("2017-10-23")
        assertThat(Weekends().between(start = monday, end = monday), `is`(emptySet()))
    }

    @Test
    fun saturday() {
        val saturday = LocalDate.parse("2017-10-28")
        assertThat(Weekends().between(start = saturday, end = saturday), `is`(setOf(saturday)))
    }

    @Test
    fun sunday() {
        val sunday = LocalDate.parse("2017-10-29")
        assertThat(Weekends().between(start = sunday, end = sunday), `is`(setOf(sunday)))
    }

    @Test
    fun wholeWeekend() {
        val saturday = LocalDate.parse("2017-10-28")
        val sunday = LocalDate.parse("2017-10-29")
        assertThat(Weekends().between(start = saturday, end = sunday), `is`(setOf(saturday, sunday)))
    }

    @Test
    fun weekendBetweenFridayAndMonday() {
        val saturday = LocalDate.parse("2017-10-28")
        val sunday = LocalDate.parse("2017-10-29")
        assertThat(Weekends().between(start = saturday.minusDays(1), end = sunday.plusDays(1)), `is`(setOf(saturday, sunday)))
    }
}