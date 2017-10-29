package org.voelk.vacation

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class WeekendsTest {
    @Test
    fun noWeekends() {
        val monday = LocalDate.parse("2017-10-23")
        assertThat(Weekends(start = monday, end = monday).days(), `is`(emptySet()))
    }

    @Test fun saturday() {
        val saturday = LocalDate.parse("2017-10-28")
        assertThat(Weekends(start = saturday, end = saturday).days(), `is`(setOf(saturday)))
    }

    @Test fun sunday() {
        val sunday = LocalDate.parse("2017-10-29")
        assertThat(Weekends(start = sunday, end = sunday).days(), `is`(setOf(sunday)))
    }

    @Test fun wholeWeekend() {
        val saturday = LocalDate.parse("2017-10-28")
        val sunday = LocalDate.parse("2017-10-29")
        assertThat(Weekends(start = saturday, end = sunday).days(), `is`(setOf(saturday, sunday)))
    }
    @Test fun weekendBetweenFridayAndMonday(){
        val saturday = LocalDate.parse("2017-10-28")
        val sunday = LocalDate.parse("2017-10-29")
        assertThat(Weekends(start = saturday.minusDays(1), end = sunday.plusDays(1)).days(), `is`(setOf(saturday, sunday)))
    }
}