package com.ujizin.leafy.domain.repository

import com.ujizin.leafy.domain.model.Plant
import kotlinx.coroutines.flow.Flow

/** Interface to Plant Repository implementation. */
interface PlantRepository {

    /**
     * Get all plants.
     *
     * @return list of [Plant]
     */
    fun getPlants(): Flow<List<Plant>>

    /**
     * Get plant by id.
     *
     * @param id the plant's id
     */
    fun getPlant(id: Long): Flow<Plant?>

    /**
     * Insert plant.
     *
     * @param plant plant to be added
     */
    fun insertPlant(plant: Plant): Flow<Long>

    /**
     * Insert a list of plant.
     *
     * @param plants list of plant to be added
     */
    fun insertPlants(plants: List<Plant>): Flow<Unit>

    /**
     * Update plant.
     *
     * @param plant plant to be updated
     */
    fun updatePlant(plant: Plant): Flow<Unit>

    /**
     * Delete plant.
     *
     * @param plant plant to be deleted
     */
    fun deletePlant(plant: Plant): Flow<Unit>

    /**
     * Get draft plant.
     *
     * @return plant hold in memory
     */
    fun getDraftPlant(): Flow<Plant>

    /**
     * Update or add draft plant.
     *
     * @param plant plant to be added
     */
    fun updateDraftPlant(plant: Plant): Flow<Unit>

    /**
     * Find plant by sentence (title or description).
     *
     * @param sentence sentence to find plants
     */
    fun findPlantBySentence(sentence: String): Flow<List<Plant>>
}
