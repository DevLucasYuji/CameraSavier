package com.ujizin.leafy.core.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ujizin.leafy.core.local.model.PlantEntity

/** Dao class to handle with [PlantEntity] in memory. */
@Dao
interface PlantMemoryDao {

    @Query("SELECT * FROM plant LIMIT 1") suspend fun getDraftPlant(): PlantEntity?

    /** Get [PlantEntity] by id. */
    @Query("SELECT * FROM plant where plant_id= :plantId LIMIT 1")
    suspend fun getById(plantId: Long): PlantEntity?

    /** Insert a new [PlantEntity]. */
    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun insert(vararg plant: PlantEntity)

    /**
     * Update a [PlantEntity] passed on parameter.
     *
     * @param plant [PlantEntity] to be updated
     */
    @Update suspend fun update(plant: PlantEntity)
}
