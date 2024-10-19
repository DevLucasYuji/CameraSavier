package com.ujizin.leafy.core.repository.implementation

import com.ujizin.leafy.core.repository.datasource.AlarmDataSource
import com.ujizin.leafy.core.repository.mapper.AlarmMapper
import com.ujizin.leafy.domain.dispatcher.IoDispatcher
import com.ujizin.leafy.domain.model.Alarm
import com.ujizin.leafy.domain.repository.AlarmRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

internal class AlarmRepositoryImpl
@Inject
constructor(
    private val dataSource: AlarmDataSource,
    private val mapper: AlarmMapper,
    @IoDispatcher val dispatcher: CoroutineDispatcher,
) : AlarmRepository {

    override fun insertAlarm(alarm: Alarm) =
        flow { emit(dataSource.insertAlarm(mapper.toRepo(alarm))) }.flowOn(dispatcher)

    override fun getAlarms(plantId: Long) =
        flow { emit(dataSource.getAlarmsByPlantId(plantId).map(mapper::toDomain)) }
            .flowOn(dispatcher)

    override fun getAllAlarms() =
        flow { emit(dataSource.getAlarms().map(mapper::toDomain)) }.flowOn(dispatcher)

    override fun getAlarmById(id: Long) =
        flow { emit(mapper.toDomain(dataSource.getAlarmById(id))) }.flowOn(dispatcher)

    override fun updateAlarm(alarm: Alarm) =
        flow { emit(dataSource.updateAlarm(mapper.toRepo(alarm))) }.flowOn(dispatcher)

    override fun deleteAlarmByPlantId(plantId: Long) =
        flow { emit(dataSource.deleteAlarmByPlantId(plantId)) }.flowOn(dispatcher)
}
