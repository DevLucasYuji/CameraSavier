package com.ujizin.leafy.core.ui.extensions

import android.content.Context
import androidx.annotation.StringRes
import com.ujizin.leafy.core.components.R
import com.ujizin.leafy.domain.model.WeekDay
import java.util.Calendar

val currentDay: WeekDay
    get() = WeekDay.entries[Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1]

val WeekDay.displayNameRes: Int
    @StringRes
    get() =
        when (this) {
            WeekDay.Sunday -> R.string.sunday
            WeekDay.Monday -> R.string.monday
            WeekDay.Tuesday -> R.string.tuesday
            WeekDay.Wednesday -> R.string.wednesday
            WeekDay.Thursday -> R.string.thursday
            WeekDay.Friday -> R.string.friday
            WeekDay.Saturday -> R.string.saturday
        }

fun WeekDay.getShortDay(context: Context, useCapitalize: Boolean = true) =
    context.getString(displayNameRes).substring(0, 3).run {
        if (useCapitalize) capitalize() else this
    }

fun List<WeekDay>.getDisplayName(context: Context, useCapitalize: Boolean = true): String {
    if (isEmpty()) return String.Empty

    if (size == WeekDay.entries.size) {
        return context.getString(R.string.daily)
    }
    if (size == 1) return context.getString(first().displayNameRes)

    val shortNames = map { it.getShortDay(context, useCapitalize) }

    return when {
        isOrdinalConsecutive() ->
            context.getString(R.string.day_to_day, shortNames.first(), shortNames.last())

        else -> shortNames.joinToString(separator = ", ")
    }
}

fun List<WeekDay>.reorderByCurrentDay(day: WeekDay = currentDay): List<WeekDay> {
    val sortedList = sortedBy(WeekDay::ordinal)
    val index = sortedList.indexOfFirst { it.ordinal >= day.ordinal }.coerceAtLeast(0)

    return sortedList.drop(index) + sortedList.take(index)
}

fun List<WeekDay>.getNearestDay(hours: Int, minutes: Int, day: WeekDay = currentDay): WeekDay {
    check(isNotEmpty()) { "List must no be null" }
    if (size == 1) return first()
    return reorderByCurrentDay(day).first { day != it || !day.isDayAlreadyPassed(hours, minutes) }
}

operator fun WeekDay.plus(other: Int): WeekDay {
    val index = (ordinal + other) % WeekDay.entries.size
    return WeekDay.entries[index]
}

fun WeekDay.isDayAlreadyPassed(hours: Int, minutes: Int): Boolean {
    if (currentDay > this) return true
    if (currentDay < this) return false

    return with(Calendar.getInstance()) {
        val currentTimeInMillis = timeInMillis
        set(Calendar.HOUR_OF_DAY, hours)
        set(Calendar.MINUTE, minutes)
        set(Calendar.SECOND, 0)

        currentTimeInMillis > timeInMillis
    }
}
