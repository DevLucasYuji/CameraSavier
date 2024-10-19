package com.ujizin.leafy.alarm.scheduler

import android.app.AlarmManager
import android.os.Bundle
import androidx.core.os.bundleOf
import com.ujizin.leafy.alarm.receiver.AlarmReceiver
import com.ujizin.leafy.core.ui.extensions.currentDay
import com.ujizin.leafy.core.ui.extensions.getNearestDay
import com.ujizin.leafy.domain.model.Alarm
import com.ujizin.leafy.domain.model.WeekDay

/** Create alarm scheduler for Android Alarm Manager system. */
interface AlarmScheduler {

    /**
     * Schedule alarm on Android System.
     *
     * @param type alarm manager's type, default is rtc wakeup
     * @param dayOfWeek the alarm's day of the week
     * @param hours the alarm's hours
     * @param minutes the alarm's minutes
     * @param ringtoneUri the alarm's ringtone uri
     * @param requestCode alarm's request code
     * @param bundle bundle for pending intent
     */
    fun scheduleAlarm(
        type: Int = AlarmManager.RTC_WAKEUP,
        dayOfWeek: Int,
        hours: Int,
        minutes: Int,
        ringtoneUri: String,
        requestCode: Int = 0,
        bundle: Bundle = Bundle.EMPTY,
    )

    /**
     * Cancel alarm scheduled on Android System.
     *
     * @param requestCode alarm's request code
     */
    fun cancelAlarm(requestCode: Int = 0)

    /**
     * Schedule alarm on Android System.
     *
     * @param alarm alarm to be scheduled.
     */
    fun scheduleAlarm(alarm: Alarm, day: WeekDay = currentDay) =
        scheduleAlarm(
            dayOfWeek = alarm.weekDays.getNearestDay(alarm.hours, alarm.minutes, day).ordinal + 1,
            hours = alarm.hours,
            minutes = alarm.minutes,
            ringtoneUri = alarm.ringtoneUriContent,
            requestCode = alarm.id.toInt(),
            bundle = bundleOf(AlarmReceiver.ALARM_ID_EXTRA to alarm.id),
        )
}
