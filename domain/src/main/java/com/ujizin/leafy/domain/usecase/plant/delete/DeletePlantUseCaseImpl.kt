package com.ujizin.leafy.domain.usecase.plant.delete

import com.ujizin.leafy.domain.model.Plant
import com.ujizin.leafy.domain.repository.PlantRepository

internal class DeletePlantUseCaseImpl(private val plantRepository: PlantRepository) :
    DeletePlantUseCase {

    override fun invoke(plant: Plant) = plantRepository.deletePlant(plant)
}
