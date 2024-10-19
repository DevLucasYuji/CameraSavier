package com.ujizin.leafy.domain.usecase.plant.find

import com.ujizin.leafy.domain.repository.PlantRepository
import com.ujizin.leafy.domain.result.asResult

internal class FindPlantUseCaseImpl(private val repository: PlantRepository) : FindPlantUseCase {

    override fun invoke(sentence: String) = repository.findPlantBySentence(sentence).asResult()
}
