package com.ujizin.leafy.core.repository.implementation

import com.ujizin.leafy.core.repository.datasource.PlantDataSource
import com.ujizin.leafy.core.repository.mapper.PlantMapper
import com.ujizin.leafy.core.repository.utils.createDraft
import com.ujizin.leafy.domain.dispatcher.IoDispatcher
import com.ujizin.leafy.domain.model.Plant
import com.ujizin.leafy.domain.repository.PlantRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

internal class PlantRepositoryImpl
@Inject
constructor(
    private val dataSource: PlantDataSource,
    private val mapper: PlantMapper,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : PlantRepository {

    override fun getPlants(): Flow<List<Plant>> =
        flow { emit(mapper.toDomain(dataSource.getPlants())) }.flowOn(dispatcher)

    override fun getPlant(id: Long): Flow<Plant?> =
        flow { emit(dataSource.getPlant(id)?.let(mapper::toDomain)) }.flowOn(dispatcher)

    override fun insertPlant(plant: Plant) =
        flow { emit(dataSource.insertPlant(mapper.toRepo(plant))) }.flowOn(dispatcher)

    override fun insertPlants(plants: List<Plant>) =
        flow { emit(dataSource.insertPlants(mapper.toRepo(plants))) }.flowOn(dispatcher)

    override fun updatePlant(plant: Plant) =
        flow { emit(dataSource.updatePlant(mapper.toRepo(plant))) }.flowOn(dispatcher)

    override fun deletePlant(plant: Plant) =
        flow { emit(dataSource.deletePlant(mapper.toRepo(plant))) }.flowOn(dispatcher)

    override fun findPlantBySentence(sentence: String) =
        flow { emit(dataSource.findPlantBySentence(sentence).map(mapper::toDomain)) }
            .flowOn(dispatcher)

    override fun getDraftPlant() =
        flow { emit(dataSource.getDraftPlant()?.let(mapper::toDomain) ?: createDraft()) }
            .flowOn(dispatcher)

    override fun updateDraftPlant(plant: Plant) =
        flow { emit(dataSource.updateDraftPlant(mapper.toRepo(plant))) }.flowOn(dispatcher)
}
