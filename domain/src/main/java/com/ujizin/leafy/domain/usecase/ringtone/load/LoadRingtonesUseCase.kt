package com.ujizin.leafy.domain.usecase.ringtone.load

import com.ujizin.leafy.domain.model.Ringtone
import kotlinx.coroutines.flow.Flow

/** Get all ringtones from android system source. */
interface LoadRingtonesUseCase {

    operator fun invoke(): Flow<List<Ringtone>>
}
