package com.ujizin.leafy.domain.usecase.alarm.add

import com.ujizin.leafy.domain.model.Alarm
import kotlinx.coroutines.flow.Flow

/** Add alarm use case. */
interface AddAlarmUseCase {

    /**
     * Add alarm on data source.
     *
     * @param alarm alarm to be added
     */
    operator fun invoke(alarm: Alarm): Flow<Long>
}
