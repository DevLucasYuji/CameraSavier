package com.ujizin.leafy.core.local.datasource

import com.ujizin.leafy.core.local.dao.PlantDao
import com.ujizin.leafy.core.local.dao.PlantMemoryDao
import com.ujizin.leafy.core.local.mapper.PlantMapper
import com.ujizin.leafy.core.repository.datasource.PlantDataSource
import com.ujizin.leafy.core.repository.model.Plant

internal class PlantLocalDataSource(
    private val plantDao: PlantDao,
    private val memoryPlantDao: PlantMemoryDao,
    private val mapper: PlantMapper,
) : PlantDataSource {

    override suspend fun insertPlant(plant: Plant): Long {
        return plantDao.insert(mapper.toPlantEntity(plant))
    }

    override suspend fun insertPlants(plants: List<Plant>) {
        val plantsEntities = plants.map(mapper::toPlantEntity).toTypedArray()
        plantDao.insertAll(*plantsEntities)
    }

    override suspend fun getPlants(): List<Plant> = plantDao.getAll().map(mapper::toPlant)

    override suspend fun getPlant(id: Long): Plant? = plantDao.findById(id)?.let(mapper::toPlant)

    override suspend fun updatePlant(plant: Plant) {
        plantDao.update(mapper.toPlantEntity(plant))
    }

    override suspend fun deletePlant(plant: Plant) {
        plantDao.delete(mapper.toPlantEntity(plant))
    }

    override suspend fun updateDraftPlant(plant: Plant) =
        with(memoryPlantDao) {
            val plantEntity = mapper.toPlantEntity(plant)
            val hasPlant = getById(plantEntity.id) != null
            when {
                hasPlant -> update(plantEntity)
                else -> insert(plantEntity)
            }
        }

    override suspend fun findPlantBySentence(sentence: String): List<Plant> {
        return plantDao.findBySentence(sentence).map(mapper::toPlant)
    }

    override suspend fun getDraftPlant(): Plant? {
        return memoryPlantDao.getDraftPlant()?.let(mapper::toPlant)
    }
}
