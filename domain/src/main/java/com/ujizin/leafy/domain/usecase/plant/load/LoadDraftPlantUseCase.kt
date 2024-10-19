package com.ujizin.leafy.domain.usecase.plant.load

import com.ujizin.leafy.domain.model.Plant
import com.ujizin.leafy.domain.result.Result
import kotlinx.coroutines.flow.Flow

/** Use case to insert draft plant in the data source. */
interface LoadDraftPlantUseCase {

    /** Get draft plant. */
    operator fun invoke(): Flow<Result<Plant>>
}
