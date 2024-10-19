package com.ujizin.leafy.domain.repository

import com.ujizin.leafy.domain.model.Alarm
import kotlinx.coroutines.flow.Flow

/** Interface to Alarm Repository implementation. */
interface AlarmRepository {

    /**
     * Insert new alarm on data source.
     *
     * @param alarm alarm to be added
     */
    fun insertAlarm(alarm: Alarm): Flow<Long>

    /**
     * Get alarms by plant id.
     *
     * @param plantId the plant's id
     */
    fun getAlarms(plantId: Long): Flow<List<Alarm>>

    /** Get all alarms. */
    fun getAllAlarms(): Flow<List<Alarm>>

    /**
     * Get alarm by id.
     *
     * @param id the alarm's id
     */
    fun getAlarmById(id: Long): Flow<Alarm>

    /**
     * Update alarm on data source.
     *
     * @param alarm alarm to be updated
     */
    fun updateAlarm(alarm: Alarm): Flow<Unit>

    /**
     * Delete alarms on data source by plant id.
     *
     * @param plantId the alarms plant's id to be deleted
     */
    fun deleteAlarmByPlantId(plantId: Long): Flow<Unit>
}
