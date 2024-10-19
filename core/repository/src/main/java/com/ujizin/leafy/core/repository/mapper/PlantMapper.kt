package com.ujizin.leafy.core.repository.mapper

import com.ujizin.leafy.core.repository.model.Plant as DataPlant
import com.ujizin.leafy.domain.model.Plant

/** Plant mapper between domain and data modules. */
internal class PlantMapper {

    fun toDomain(plants: List<DataPlant>) = plants.map { toDomain(it) }

    fun toDomain(plant: DataPlant) =
        with(plant) { Plant(id, title, description, filePath, favorite, albumId) }

    fun toRepo(plants: List<Plant>) = plants.map { toRepo(it) }

    fun toRepo(plant: Plant) =
        with(plant) { DataPlant(id, title, filePath, description, favorite, albumId) }
}
