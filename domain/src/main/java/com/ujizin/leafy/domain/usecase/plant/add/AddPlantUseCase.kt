package com.ujizin.leafy.domain.usecase.plant.add

import com.ujizin.leafy.domain.model.Plant
import kotlinx.coroutines.flow.Flow

/** Use case to insert plant in the data source. */
interface AddPlantUseCase {

    /**
     * Add a plant.
     *
     * @param plant plants to be added
     */
    operator fun invoke(plant: Plant): Flow<Long>
}
