package com.ujizin.leafy.alarm.extensions

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import com.ujizin.leafy.alarm.AlarmService

/** Get alarm manager instance from context */
val Context.alarmManager
    get() = getSystemService(Context.ALARM_SERVICE) as AlarmManager

/** Start alarm permission intent if there's no alarm permission */
@SuppressLint("InlinedApi")
fun Context.startAlarmPermission() {
    if (alarmManager.hasAlarmPermission) return

    val intent = Intent().apply { action = Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM }

    startActivity(intent)
}

/** Check if has permission in schedule alarms. */
val AlarmManager.hasAlarmPermission
    get() =
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> canScheduleExactAlarms()
            else -> true
        }

/** Get alarm service intent */
internal fun Context.getAlarmIntent(action: String? = null) =
    Intent(this, AlarmService::class.java).apply { this.action = action }
