package com.ujizin.leafy.domain.usecase.ringtone.load

import com.ujizin.leafy.domain.model.Ringtone
import com.ujizin.leafy.domain.repository.RingtoneRepository
import kotlinx.coroutines.flow.Flow

internal class LoadRingtonesUseCaseImpl(private val ringtoneRepository: RingtoneRepository) :
    LoadRingtonesUseCase {

    override fun invoke(): Flow<List<Ringtone>> = ringtoneRepository.getRingtones()
}
