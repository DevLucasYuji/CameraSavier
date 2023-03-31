package com.ujizin.leafy.domain.usecase.plant

import com.ujizin.leafy.domain.model.Plant
import com.ujizin.leafy.domain.result.Result
import kotlinx.coroutines.flow.Flow

/**
 * Find plant use case.
 * */
interface FindPlant {

    /**
     * Find plant by sentence.
     *
     * @param sentence sentence to find plant in title or description.
     * */
    operator fun invoke(sentence: String): Flow<Result<List<Plant>>>
}
