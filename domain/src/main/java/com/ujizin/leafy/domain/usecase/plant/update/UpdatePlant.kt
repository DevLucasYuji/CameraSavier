package com.ujizin.leafy.domain.usecase.plant.update

import com.ujizin.leafy.domain.model.Plant
import kotlinx.coroutines.flow.Flow

/** Use case to update plant in the data source. */
interface UpdatePlant {

    /**
     * Update a plant.
     *
     * @param plant plant to be updated
     */
    operator fun invoke(plant: Plant): Flow<Unit>
}
