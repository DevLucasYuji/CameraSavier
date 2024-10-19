package com.ujizin.leafy.domain.usecase.plant.add

import com.ujizin.leafy.domain.repository.PlantRepository
import java.io.File
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map

internal class AddDraftPlantUseCaseImpl(private val plantRepository: PlantRepository) :
    AddDraftPlantUseCase {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun invoke(title: String?, file: File?, description: String?): Flow<Unit> =
        plantRepository
            .getDraftPlant()
            .map { plant ->
                plant.copy(
                    title = title ?: plant.title,
                    description = description ?: plant.description,
                    filePath = file?.path ?: plant.filePath,
                )
            }
            .flatMapConcat { plant -> plantRepository.updateDraftPlant(plant) }
}
