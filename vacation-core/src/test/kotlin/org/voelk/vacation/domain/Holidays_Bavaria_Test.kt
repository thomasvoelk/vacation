package org.voelk.vacation.domain

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class Holidays_Bavaria_Test {
    @Test
    fun noHolidays() {
        val moday_noHoliday = LocalDate.parse("2017-10-23")
        val sunday_noHoliday = LocalDate.parse("2017-10-29")
        //monday only
        assertThat(Holidays().between(start = moday_noHoliday, end = moday_noHoliday, location = "by"), `is`(emptySet()))
        //sunday only
        assertThat(Holidays().between(start = sunday_noHoliday, end = sunday_noHoliday, location = "by"), `is`(emptySet()))
        //moday to sunday
        assertThat(Holidays().between(start = moday_noHoliday, end = sunday_noHoliday, location = "by"), `is`(emptySet()))
    }


    @Test
    fun bavarianHolidays() {
        val thursday_corpus_christi = LocalDate.parse("2017-06-15")
        //only the holiday
        assertThat(Holidays().between(start = thursday_corpus_christi, end = thursday_corpus_christi, location = "by"), `is`(setOf(thursday_corpus_christi)))
        // the holiday and one non-holiday after
        assertThat(Holidays().between(start = thursday_corpus_christi, end = thursday_corpus_christi.plusDays(1), location = "by"), `is`(setOf(thursday_corpus_christi)))
        // the holiday and one non-holiday before
        assertThat(Holidays().between(start = thursday_corpus_christi.minusDays(1), end = thursday_corpus_christi, location = "by"), `is`(setOf(thursday_corpus_christi)))
        // the holiday between two non-holidays
        assertThat(Holidays().between(start = thursday_corpus_christi.minusDays(1), end = thursday_corpus_christi.plusDays(1), location = "by"), `is`(setOf(thursday_corpus_christi)))
    }

}