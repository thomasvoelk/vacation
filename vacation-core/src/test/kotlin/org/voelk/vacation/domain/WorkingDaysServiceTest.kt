package org.voelk.vacation.domain

import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.*
import org.junit.jupiter.api.*
import org.mockito.*
import org.mockito.Mockito.*
import org.voelk.vacation.api.*
import java.time.*
import kotlin.test.*

class WorkingDaysServiceTest {
    private val workingPlace = WorkingPlace("by")
    private val today = LocalDate.now()
    private var days = mock(Days::class.java)
    private var holidays = mock(Holidays::class.java)
    private var weekends = mock(Weekends::class.java)
    private val service = WorkingDaysService(days, holidays, weekends)

    @BeforeEach
    fun setup() {
        `when`(days.between(any(), any())).thenReturn(emptySet())
        `when`(holidays.between(any(), any(), anyString())).thenReturn(emptySet())
        `when`(weekends.between(any(), any())).thenReturn(emptySet())
    }

    @Test
    fun threeDays_oneHoliday_oneWeekendDay_isOneWorkingDay() {
        val tomorrow = today.plusDays(1)
        val dayAfterTomorrow = tomorrow.plusDays(1)
        `when`(days.between(any(), any())).thenReturn(setOf(today, tomorrow, dayAfterTomorrow))
        `when`(holidays.between(any(), any(), anyString())).thenReturn(setOf(tomorrow))
        `when`(weekends.between(any(), any())).thenReturn(setOf(dayAfterTomorrow))

        assertThat(service.workingDaysBetween(today, dayAfterTomorrow, workingPlace), `is`(1.0))
    }

    @Test
    fun maxOf_1000_days_is_ok() {
        val muchLater = today.plusDays(WorkingDaysCalculator.MAX_RANGE - 1L)
        assertThat(service.workingDaysBetween(today, muchLater, workingPlace), `is`(0.0))
    }

    @Test()
    fun moreThan1000_notAllowed() {
        assertFailsWith(IllegalArgumentException::class, {
            service.workingDaysBetween(today, today.plusDays(WorkingDaysCalculator.MAX_RANGE.toLong()), workingPlace)
        })
    }

    @Test
    fun endMustNotBeBeforeStart() {
        assertFailsWith(IllegalArgumentException::class, {
            service.workingDaysBetween(today, today.minusDays(1), workingPlace)
        })
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> any(): T {
        Mockito.any<T>()
        return null as T
    }

}