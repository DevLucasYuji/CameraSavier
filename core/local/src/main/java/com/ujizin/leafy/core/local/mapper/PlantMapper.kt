package com.ujizin.leafy.core.local.mapper

import com.ujizin.leafy.core.local.model.PlantEntity
import com.ujizin.leafy.core.repository.model.Plant

/** Mapper between [Plant] and [PlantEntity] */
internal class PlantMapper {

    fun toPlant(plantEntity: PlantEntity) =
        with(plantEntity) { Plant(id, title, filePath, description, favorite, albumId) }

    fun toPlantEntity(plant: Plant) =
        with(plant) { PlantEntity(id, title, filePath, description, favorite, albumId) }
}
