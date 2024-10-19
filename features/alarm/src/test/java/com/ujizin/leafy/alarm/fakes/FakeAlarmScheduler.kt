package com.ujizin.leafy.alarm.fakes

import android.os.Bundle
import com.ujizin.leafy.alarm.scheduler.AlarmScheduler

class FakeAlarmScheduler : AlarmScheduler {

    data class FakeAlarm(
        val id: Int,
        val type: Int,
        val dayOfWeek: Int,
        val hours: Int,
        val minutes: Int,
        val ringtoneUri: String,
        val bundle: Bundle,
    )

    private val _alarms = mutableListOf<FakeAlarm>()
    val alarms
        get() = _alarms.toList()

    override fun scheduleAlarm(
        type: Int,
        dayOfWeek: Int,
        hours: Int,
        minutes: Int,
        ringtoneUri: String,
        requestCode: Int,
        bundle: Bundle,
    ) {
        _alarms +=
            FakeAlarm(
                id = requestCode,
                dayOfWeek = dayOfWeek,
                type = type,
                hours = hours,
                minutes = minutes,
                ringtoneUri = ringtoneUri,
                bundle = bundle,
            )
    }

    override fun cancelAlarm(requestCode: Int) {
        val alarm = _alarms.first { it.id == requestCode }
        _alarms -= alarm
    }
}
