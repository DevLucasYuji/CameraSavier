package com.ujizin.leafy.core.repository.model

/**
 * Contract Alarm Model to PlantDataSource
 *
 * @param id the alarm id
 * @param plantId the plant's id
 * @param ringtoneUriString the alarm's ringtone uri
 * @param hours alarm's hour
 * @param enabled check if alarm is enabled or not
 * @param minutes alarm's minutes
 * @param weekDays day of the week for alarm
 */
data class Alarm(
    val id: Long = 0,
    val plantId: Long,
    val ringtoneUriString: String,
    val hours: Int,
    val enabled: Boolean,
    val minutes: Int,
    val weekDays: List<String>,
)
