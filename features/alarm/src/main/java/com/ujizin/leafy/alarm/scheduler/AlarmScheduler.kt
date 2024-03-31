package com.ujizin.leafy.alarm.scheduler

import android.app.AlarmManager
import android.os.Bundle
import androidx.core.os.bundleOf
import com.ujizin.leafy.alarm.receiver.AlarmReceiver
import com.ujizin.leafy.domain.model.Alarm

/**
 * Create alarm scheduler for Android Alarm Manager system.
 * */
interface AlarmScheduler {

    /**
     * Schedule alarm on Android System.
     *
     * @param type alarm manager's type, default is rtc wakeup
     * @param hours the alarm's hours
     * @param minutes the alarm's minutes
     * @param ringtoneUri the alarm's ringtone uri
     * @param bundle bundle for pending intent
     * */
    fun scheduleAlarm(
        type: Int = AlarmManager.RTC_WAKEUP,
        hours: Int,
        minutes: Int,
        ringtoneUri: String,
        requestCode: Int = 0,
        bundle: Bundle = Bundle.EMPTY,
    )

    /**
     * Schedule alarm on Android System.
     *
     * @param alarm alarm to be scheduled.
     * */
    fun scheduleAlarm(alarm: Alarm) = scheduleAlarm(
        hours = alarm.hours,
        minutes = alarm.minutes,
        ringtoneUri = alarm.ringtoneUriContent,
        requestCode = alarm.id.toInt(),
        bundle = bundleOf(AlarmReceiver.ALARM_ID_EXTRA to alarm.id),
    )
}
