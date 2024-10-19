package com.ujizin.leafy.domain.usecase.plant.load

import com.ujizin.leafy.domain.model.Plant
import com.ujizin.leafy.domain.result.Result
import kotlinx.coroutines.flow.Flow

/** Load plant use case */
interface LoadPlantUseCase {

    /**
     * Load plant by id.
     *
     * @param id the plant's id
     */
    operator fun invoke(id: Long): Flow<Result<Plant?>>
}
