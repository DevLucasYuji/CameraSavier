package com.ujizin.leafy.core.repository.datasource

import com.ujizin.leafy.core.repository.model.Plant

/** Interface Plant to data source implementation. */
interface PlantDataSource {

    /**
     * Inserts a new [Plant].
     *
     * @param plant [Plant] to be added
     * @return return the new plant's id
     */
    suspend fun insertPlant(plant: Plant): Long

    /**
     * Inserts a new list of [Plant].
     *
     * @param plants list of [Plant] to be added
     */
    suspend fun insertPlants(plants: List<Plant>)

    /**
     * Get all [Plant] from data source.
     *
     * @return all [Plant] added.
     */
    suspend fun getPlants(): List<Plant>

    /**
     * Get plant by id.
     *
     * @param id the plant's id.
     */
    suspend fun getPlant(id: Long): Plant?

    /**
     * Update the [Plant] passed on parameter.
     *
     * @param plant [Plant] to be updated
     */
    suspend fun updatePlant(plant: Plant)

    /**
     * Delete the plant passed on parameter.
     *
     * @param plant plant to be deleted
     */
    suspend fun deletePlant(plant: Plant)

    /**
     * Add or update the draft [Plant] passed on parameter.
     *
     * @param plant [Plant] to be added in memory.
     */
    suspend fun updateDraftPlant(plant: Plant)

    /** Get draft [Plant]. */
    suspend fun getDraftPlant(): Plant?

    /**
     * Find plant by title or description.
     *
     * @param sentence sentence to find plants
     */
    suspend fun findPlantBySentence(sentence: String): List<Plant>
}
