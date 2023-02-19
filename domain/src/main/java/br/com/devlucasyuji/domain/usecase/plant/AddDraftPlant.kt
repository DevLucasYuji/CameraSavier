package br.com.devlucasyuji.domain.usecase.plant

import kotlinx.coroutines.flow.Flow
import java.io.File

/**
 * Use case to insert draft plant in the data source.
 * */
interface AddDraftPlant {

    /**
     * Add draft plant.
     *
     * @param title plant's title to be added
     * @param date plant's date to be added
     * @param file plant's file to be added
     * @param description plant's description to be added
     * */
    operator fun invoke(
        title: String? = null,
        date: String? = null,
        file: File? = null,
        description: String? = null,
    ): Flow<Unit>
}
