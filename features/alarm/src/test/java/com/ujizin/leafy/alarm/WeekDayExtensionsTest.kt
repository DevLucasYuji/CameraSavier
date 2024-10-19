package com.ujizin.leafy.alarm

import com.ujizin.leafy.core.ui.extensions.plus
import com.ujizin.leafy.core.ui.extensions.reorderByCurrentDay
import com.ujizin.leafy.domain.model.WeekDay
import kotlin.random.Random
import org.junit.Assert
import org.junit.Test

class WeekDayExtensionsTest {

    @Test
    fun `when sum sunday plus one, then should return monday`() {
        // Given
        val expected = WeekDay.Monday

        // When
        val actual = WeekDay.Sunday + 1

        // Then
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `when sum sunday plus random number, then should return correct day`() {
        // Given
        val random = Random.nextInt(MIN_RANDOM_NUM, MAX_RANDOM_NUM)
        val ordinal = random % WeekDay.entries.size
        val expected = WeekDay.entries[ordinal]

        // When
        val actual = WeekDay.Sunday + random

        // Then
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `when reorder by saturday as current day, then should return reordered list`() {
        // Given
        val weekDays = listOf(WeekDay.Monday, WeekDay.Saturday, WeekDay.Friday, WeekDay.Thursday)
        val currentDay = WeekDay.Saturday
        val expectDays = listOf(WeekDay.Saturday, WeekDay.Monday, WeekDay.Thursday, WeekDay.Friday)

        // When
        val reorderedDay = weekDays.reorderByCurrentDay(currentDay)

        // Then
        Assert.assertEquals(expectDays, reorderedDay)
    }

    companion object {
        private const val MIN_RANDOM_NUM = 0
        private const val MAX_RANDOM_NUM = 10
    }
}
