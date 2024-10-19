package com.ujizin.leafy.domain.usecase.alarm.load

import com.ujizin.leafy.domain.model.Alarm
import com.ujizin.leafy.domain.repository.AlarmRepository
import com.ujizin.leafy.domain.result.Result
import com.ujizin.leafy.domain.result.asResult
import kotlinx.coroutines.flow.Flow

class LoadAlarmsUseCaseImpl(private val repository: AlarmRepository) : LoadAlarmsUseCase {

    override fun invoke(plantId: Long): Flow<Result<List<Alarm>>> =
        if (plantId != LoadAlarmsUseCase.INVALID_PLANT_ID) {
            repository.getAlarms(plantId).asResult()
        } else {
            repository.getAllAlarms().asResult()
        }
}
