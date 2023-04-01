package com.ujizin.leafy.core.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Alarm [Entity]
 *
 * @param id the alarm id
 * @param ringtoneUriString the ring's alarm
 * @param plantId the plant id linked to the alarm
 * */
@Entity(
    tableName = "alarm",
    foreignKeys = [
        ForeignKey(
            entity = PlantEntity::class,
            parentColumns = ["plant_id"],
            childColumns = ["plant_id"],
            onDelete = ForeignKey.SET_DEFAULT,
        ),
    ],
)
data class AlarmEntity(
    @ColumnInfo(name = "alarm_id")
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "alarm_ringtone")
    val ringtoneUriString: String,
    @ColumnInfo(name = "plant_id")
    val plantId: Long,
    @ColumnInfo(name = "repeat_interval_in_millis")
    val repeatIntervalTimeInMillis: Long,
    @ColumnInfo(name = "hours")
    val hours: Int,
    @ColumnInfo(name = "minutes")
    val minutes: Int,
)
