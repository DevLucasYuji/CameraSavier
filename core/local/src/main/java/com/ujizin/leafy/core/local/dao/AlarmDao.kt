package com.ujizin.leafy.core.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ujizin.leafy.core.local.model.AlarmEntity

/** [Dao] class to handle with [AlarmEntity]. */
@Dao
interface AlarmDao {

    /**
     * Get all [AlarmEntity].
     *
     * @return list of [AlarmEntity]
     */
    @Query("SELECT * FROM alarm") suspend fun getAll(): List<AlarmEntity>

    /**
     * Get all [AlarmEntity] by plant id.
     *
     * @param plantId the plant's id
     */
    @Query("SELECT * FROM alarm WHERE plant_id = :plantId")
    suspend fun getByPlantId(plantId: Long): List<AlarmEntity>

    /**
     * Find all [AlarmEntity] by plant id.
     *
     * @param plantId plant id to find all [AlarmEntity]
     * @return list of [AlarmEntity]
     */
    @Query("SELECT * FROM alarm WHERE plant_id = :plantId")
    suspend fun findByPlantId(plantId: Long): List<AlarmEntity>?

    /**
     * Find alarm by id.
     *
     * @param id the alarm's id
     */
    @Query("SELECT * FROM alarm WHERE id = :id") suspend fun findAlarmById(id: Long): AlarmEntity

    /**
     * Insert a new [AlarmEntity].
     *
     * @param alarm [AlarmEntity] to be added
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun insert(alarm: AlarmEntity): Long

    /**
     * Update [AlarmEntity] passed on parameter.
     *
     * @param alarm [AlarmEntity] to be updated
     */
    @Update suspend fun update(alarm: AlarmEntity)

    /**
     * Delete [AlarmEntity] passed on parameter.
     *
     * @param alarm [AlarmEntity] to be deleted
     */
    @Delete suspend fun delete(alarm: AlarmEntity)

    /**
     * Delete [AlarmEntity] by plant id.
     *
     * @param plantId the alarms plant's id to be deleted
     */
    @Query("DELETE FROM alarm WHERE plant_id = :plantId")
    suspend fun deleteAlarmByPlantId(plantId: Long)
}
