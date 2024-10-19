package com.ujizin.leafy.domain.usecase.alarm.delete

import kotlinx.coroutines.flow.Flow

/** Delete alarm use case. */
interface DeleteAlarmUseCase {

    /**
     * Delete alarm by plant id.
     *
     * @param plantId the alarms plant's id to be deleted
     */
    operator fun invoke(plantId: Long): Flow<Unit>
}
