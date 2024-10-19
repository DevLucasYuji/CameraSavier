package com.ujizin.leafy.domain.usecase.plant.load

import com.ujizin.leafy.domain.model.Plant
import com.ujizin.leafy.domain.repository.PlantRepository
import com.ujizin.leafy.domain.result.Result
import com.ujizin.leafy.domain.result.asResult
import kotlinx.coroutines.flow.Flow

internal class LoadDraftPlantUseCaseImpl(private val repository: PlantRepository) :
    LoadDraftPlantUseCase {

    override fun invoke(): Flow<Result<Plant>> = repository.getDraftPlant().asResult()
}
