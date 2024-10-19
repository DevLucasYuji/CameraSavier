package com.ujizin.leafy.domain.usecase.plant.add

import com.ujizin.leafy.domain.model.Plant
import com.ujizin.leafy.domain.repository.PlantRepository

internal class AddPlantUseCaseImpl(private val plantRepository: PlantRepository) : AddPlantUseCase {

    override fun invoke(plant: Plant) = plantRepository.insertPlant(plant)
}
