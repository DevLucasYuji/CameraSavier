package com.ujizin.leafy.domain.usecase.plant.load

import com.ujizin.leafy.domain.model.Plant
import com.ujizin.leafy.domain.result.Result
import kotlinx.coroutines.flow.Flow

/** Use case to load all plant from repository. */
interface LoadAllPlantUseCase {

    /**
     * Load all plant.
     *
     * @return list of all plant
     */
    operator fun invoke(): Flow<Result<List<Plant>>>
}
