package com.ujizin.leafy.domain.usecase.plant.add

import java.io.File
import kotlinx.coroutines.flow.Flow

/** Use case to insert draft plant in the data source. */
interface AddDraftPlantUseCase {

    /**
     * Add draft plant.
     *
     * @param title plant's title to be added
     * @param file plant's file to be added
     * @param description plant's description to be added
     */
    operator fun invoke(
        title: String? = null,
        file: File? = null,
        description: String? = null,
    ): Flow<Unit>
}
