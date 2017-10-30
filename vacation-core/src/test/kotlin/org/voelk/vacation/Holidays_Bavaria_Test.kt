package org.voelk.vacation

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class Holidays_Bavaria_Test {
    @Test fun noHolidays() {
        val moday_noHoliday = LocalDate.parse("2017-10-23")
        val sunday_noHoliday = LocalDate.parse("2017-10-29")
        //monday only
        assertThat(Holidays(start = moday_noHoliday, end = moday_noHoliday, location = "by").days(), `is`(emptySet()))
        //sunday only
        assertThat(Holidays(start = sunday_noHoliday, end = sunday_noHoliday, location = "by").days(), `is`(emptySet()))
        //moday to sunday
        assertThat(Holidays(start = moday_noHoliday, end = sunday_noHoliday, location = "by").days(), `is`(emptySet()))
    }


    @Test fun bavarianHolidays() {
        val thursday_corpus_christi = LocalDate.parse("2017-06-15")
        //only the holiday
        assertThat(Holidays(start = thursday_corpus_christi, end = thursday_corpus_christi, location = "by").days(), `is`(setOf(thursday_corpus_christi)))
        // the holiday and one non-holiday after
        assertThat(Holidays(start = thursday_corpus_christi, end = thursday_corpus_christi.plusDays(1), location = "by").days(), `is`(setOf(thursday_corpus_christi)))
        // the holiday and one non-holiday before
        assertThat(Holidays(start = thursday_corpus_christi.minusDays(1), end = thursday_corpus_christi, location = "by").days(), `is`(setOf(thursday_corpus_christi)))
        // the holiday between two non-holidays
        assertThat(Holidays(start = thursday_corpus_christi.minusDays(1), end = thursday_corpus_christi.plusDays(1), location = "by").days(), `is`(setOf(thursday_corpus_christi)))
    }

}