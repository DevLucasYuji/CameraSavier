package com.ujizin.leafy.domain.usecase.alarm.load

import com.ujizin.leafy.domain.model.Alarm
import com.ujizin.leafy.domain.result.Result
import kotlinx.coroutines.flow.Flow

/** Load alarm use case. */
interface LoadAlarmUseCase {

    /**
     * Load alarm on data source.
     *
     * @param id the alarm's id
     */
    operator fun invoke(id: Long): Flow<Result<Alarm>>
}
