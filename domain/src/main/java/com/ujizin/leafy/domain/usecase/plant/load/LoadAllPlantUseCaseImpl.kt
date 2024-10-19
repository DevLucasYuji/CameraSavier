package com.ujizin.leafy.domain.usecase.plant.load

import com.ujizin.leafy.domain.model.Plant
import com.ujizin.leafy.domain.repository.PlantRepository
import com.ujizin.leafy.domain.result.Result
import com.ujizin.leafy.domain.result.asResult
import kotlinx.coroutines.flow.Flow

internal class LoadAllPlantUseCaseImpl(private val repository: PlantRepository) :
    LoadAllPlantUseCase {

    override fun invoke(): Flow<Result<List<Plant>>> = repository.getPlants().asResult()
}
