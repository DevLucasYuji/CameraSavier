package com.ujizin.leafy.core.repository.datasource

import com.ujizin.leafy.core.repository.model.Alarm

/** Interface Alarm to data source implementation. */
interface AlarmDataSource {
    /**
     * Insert a new [Alarm].
     *
     * @param alarm [Alarm] to be added
     */
    suspend fun insertAlarm(alarm: Alarm): Long

    /**
     * Insert a new list of [Alarm].
     *
     * @param alarms list of [Alarm] to be added
     */
    suspend fun insertAlarms(alarms: List<Alarm>)

    /**
     * Get all [Alarm] from data source.
     *
     * @return all [Alarm] added
     */
    suspend fun getAlarms(): List<Alarm>

    /**
     * Get all [Alarm] from data source by plant id.
     *
     * @param plantId the plant's id
     */
    suspend fun getAlarmsByPlantId(plantId: Long): List<Alarm>

    /**
     * Update [Alarm] passed on parameter
     *
     * @param alarm [Alarm] to be updated
     */
    suspend fun updateAlarm(alarm: Alarm)

    /**
     * Delete [Alarm] passed on parameter
     *
     * @param alarm [Alarm] to be deleted
     */
    suspend fun deleteAlarm(alarm: Alarm)

    /**
     * Get alarm by id
     *
     * @param id the alarm's id
     */
    suspend fun getAlarmById(id: Long): Alarm

    /**
     * Delete alarm by plant id.
     *
     * @param plantId the alarms plant's id to be deleted
     */
    suspend fun deleteAlarmByPlantId(plantId: Long)
}
