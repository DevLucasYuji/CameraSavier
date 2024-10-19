package com.ujizin.leafy.domain.usecase.alarm.load

import com.ujizin.leafy.domain.model.Alarm
import com.ujizin.leafy.domain.result.Result
import kotlinx.coroutines.flow.Flow

/** Load alarms use case. */
interface LoadAlarmsUseCase {

    /**
     * Load alarms.
     *
     * @param plantId the plant's id
     */
    operator fun invoke(plantId: Long = INVALID_PLANT_ID): Flow<Result<List<Alarm>>>

    companion object {
        internal const val INVALID_PLANT_ID = -1L
    }
}
