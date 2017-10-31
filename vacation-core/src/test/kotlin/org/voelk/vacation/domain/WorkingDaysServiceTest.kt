package org.voelk.vacation.domain

import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.*
import org.junit.jupiter.api.*
import org.mockito.*
import org.mockito.Mockito.*
import org.voelk.vacation.api.*
import java.time.*
import java.util.stream.*
import kotlin.test.*

class WorkingDaysServiceTest {
    private val workingPlace = WorkingPlace("by")
    private val today = LocalDate.now()
    private val days = mock(Days::class.java)
    private val holidays = mock(Holidays::class.java)
    private val weekends = mock(Weekends::class.java)
    private var service = WorkingDaysService(days, holidays, weekends)

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

        assertStreamEquals(service.workingDaysBetween(today, dayAfterTomorrow, workingPlace), setOf(today).stream())
    }

    @Test
    fun maxOf_1000_days_is_ok() {
        val muchLater = today.plusDays(WorkingDaysCalculator.MAX_RANGE - 1L)
        assertStreamEquals(service.workingDaysBetween(today, muchLater, workingPlace), emptySet<LocalDate>().stream())
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

    private fun <T> assertStreamEquals(expected: Stream<T>, actual: Stream<T>) {
        val iterator1 = expected.iterator()
        val iterator2 = actual.iterator()
        while (iterator1.hasNext() && iterator2.hasNext())
            assertThat(iterator1.next(), `is`(iterator2.next()))
        assertThat(!iterator1.hasNext(), `is`(!iterator2.hasNext()))
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> any(): T {
        Mockito.any<T>()
        return null as T
    }

}