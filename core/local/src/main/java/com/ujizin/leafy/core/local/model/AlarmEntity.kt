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
 * @param hours the alarm's hours
 * @param minutes the alarm's minutes
 * @param enabled check if alarm is enabled or not
 */
@Entity(
    tableName = "alarm",
    foreignKeys =
        [
            ForeignKey(
                entity = PlantEntity::class,
                parentColumns = ["plant_id"],
                childColumns = ["plant_id"],
                onDelete = ForeignKey.SET_DEFAULT,
            )
        ],
)
data class AlarmEntity(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "ringtone") val ringtoneUriString: String,
    @ColumnInfo(name = "enabled") val enabled: Boolean,
    @ColumnInfo(name = "hours") val hours: Int,
    @ColumnInfo(name = "minutes") val minutes: Int,
    @ColumnInfo(name = "plant_id", index = true) val plantId: Long,
    @ColumnInfo(name = "week_days") val weekDays: List<String>,
)
