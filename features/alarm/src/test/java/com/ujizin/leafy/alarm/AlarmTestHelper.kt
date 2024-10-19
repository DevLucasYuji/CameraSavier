package com.ujizin.leafy.alarm

import com.ujizin.leafy.domain.model.Alarm
import com.ujizin.leafy.domain.model.WeekDay
import kotlin.random.Random

object AlarmTestHelper {

    fun createAlarm(
        id: Long = 1,
        plantId: Long = 1,
        ringtoneContent: String = "",
        enabled: Boolean = true,
        hours: Int = Random.nextInt(23),
        minutes: Int = Random.nextInt(59),
        weekDays: List<WeekDay> =
            (0..6).shuffled().take(Random.nextInt(1, 6)).map { WeekDay.entries[it] },
    ) = Alarm(id, plantId, ringtoneContent, hours, enabled, minutes, weekDays)
}
