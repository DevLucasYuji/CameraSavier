package com.ujizin.leafy.domain.repository

import com.ujizin.leafy.domain.model.Ringtone
import kotlinx.coroutines.flow.Flow

/** Ringtone repository from android system. */
interface RingtoneRepository {

    /** Get list of ringtone. */
    fun getRingtones(): Flow<List<Ringtone>>
}
