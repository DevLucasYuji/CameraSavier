package com.ujizin.leafy.alarm.scheduler

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.core.app.AlarmManagerCompat
import androidx.core.net.toUri
import com.ujizin.leafy.alarm.extensions.alarmManager
import com.ujizin.leafy.alarm.receiver.AlarmReceiver
import com.ujizin.leafy.core.ui.extensions.isDayAlreadyPassed
import com.ujizin.leafy.domain.model.WeekDay
import java.util.Calendar

internal class AlarmSchedulerImpl(private val context: Context) : AlarmScheduler {

    override fun scheduleAlarm(
        type: Int,
        dayOfWeek: Int,
        hours: Int,
        minutes: Int,
        ringtoneUri: String,
        requestCode: Int,
        bundle: Bundle,
    ) {
        setAlarm(type, dayOfWeek, hours, minutes, ringtoneUri.toUri(), requestCode, bundle)
    }

    /**
     * Set once and exact alarm.
     *
     * @param type alarm manager's type, default is rtc wakeup
     * @param hours the alarm's hours
     * @param minutes the alarm's minutes
     * @param ringtoneUri the alarm's ringtone uri
     */
    private fun setAlarm(
        type: Int,
        dayOfWeek: Int,
        hours: Int,
        minutes: Int,
        ringtoneUri: Uri,
        requestCode: Int = 0,
        bundle: Bundle,
    ) {
        AlarmManagerCompat.setExactAndAllowWhileIdle(
            context.alarmManager,
            type,
            getTimeInMillis(dayOfWeek, hours, minutes),
            createPendingIntent(ringtoneUri, requestCode, bundle),
        )
    }

    /**
     * get time from alarm in milliseconds.
     *
     * @param hours the timer's hours
     * @param minutes the timer's minutes
     */
    private fun getTimeInMillis(dayOfWeek: Int, hours: Int, minutes: Int): Long =
        with(Calendar.getInstance()) {
            set(Calendar.HOUR_OF_DAY, hours)
            set(Calendar.MINUTE, minutes)
            setDay(dayOfWeek, hours, minutes)

            return@with timeInMillis
        }

    private fun Calendar.setDay(dayOfWeek: Int, hours: Int, minutes: Int) {
        val currentDayOfWeek = get(Calendar.DAY_OF_WEEK)
        val weekDay = WeekDay.entries[currentDayOfWeek - 1]
        if (weekDay.isDayAlreadyPassed(hours, minutes)) {
            val daysUntilNextWeek = WeekDay.entries.size - currentDayOfWeek + dayOfWeek
            add(Calendar.DATE, daysUntilNextWeek)
        } else {
            set(Calendar.DAY_OF_WEEK, dayOfWeek)
        }
    }

    override fun cancelAlarm(requestCode: Int) {
        val pendingIntent = createPendingIntent(requestCode = requestCode)
        context.alarmManager.cancel(pendingIntent)
    }

    /**
     * Create pending intent for alarm
     *
     * @param ringtoneUri the alarm's ringtone
     */
    private fun createPendingIntent(
        ringtoneUri: Uri = Uri.EMPTY,
        requestCode: Int = 0,
        bundle: Bundle = Bundle.EMPTY,
    ): PendingIntent =
        PendingIntent.getBroadcast(
            context,
            requestCode,
            Intent(context, AlarmReceiver::class.java).apply {
                action = AlarmReceiver.SCHEDULE_ALARM_ACTION
                putExtra(AlarmReceiver.RINGTONE_CONTENT_EXTRA, ringtoneUri.toString())
                putExtras(bundle)
            },
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT,
        )
}
