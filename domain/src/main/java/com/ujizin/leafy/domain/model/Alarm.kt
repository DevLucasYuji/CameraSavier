package com.ujizin.leafy.domain.model

/**
 * Alarm Model
 *
 * @param id the alarm id
 * @param plantId the plant's id
 * @param ringtoneUriContent the alarm's ringtone
 * @param hours the alarm's hours
 * @param enabled check if alarm is enabled or not
 * @param minutes the alarm's minutes
 * @param weekDays day of the week for alarm
 */
data class Alarm(
    val id: Long = 0,
    val plantId: Long,
    val ringtoneUriContent: String,
    val hours: Int,
    val enabled: Boolean,
    val minutes: Int,
    val weekDays: List<WeekDay> = WeekDay.entries.toList(),
)
