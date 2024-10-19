package com.ujizin.leafy.alarm.ui.components.timerbox

import com.ujizin.leafy.core.ui.extensions.toDecimalFormat

internal fun List<Int>.toDecimalFormat() = map { it.toDecimalFormat() }

internal enum class TimeUnit(val numbers: List<String>) {
    Hour((0..23).toList().toDecimalFormat()),
    Minutes((0..59).toList().toDecimalFormat()),
}
