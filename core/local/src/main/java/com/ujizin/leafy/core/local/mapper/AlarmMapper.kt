package com.ujizin.leafy.core.local.mapper

import com.ujizin.leafy.core.local.model.AlarmEntity
import com.ujizin.leafy.core.repository.model.Alarm

/** Mapper between [Alarm] and [AlarmEntity] */
internal class AlarmMapper {

    fun toAlarmEntity(alarm: Alarm) =
        with(alarm) {
            AlarmEntity(
                id = id,
                ringtoneUriString = ringtoneUriString,
                minutes = minutes,
                hours = hours,
                enabled = enabled,
                plantId = plantId,
                weekDays = weekDays,
            )
        }

    fun toAlarm(alarm: AlarmEntity) =
        with(alarm) {
            Alarm(
                id = id,
                plantId = plantId,
                ringtoneUriString = ringtoneUriString,
                hours = hours,
                enabled = enabled,
                minutes = minutes,
                weekDays = weekDays,
            )
        }
}
