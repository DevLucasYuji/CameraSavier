package com.ujizin.leafy.domain.usecase.alarm.load

import com.ujizin.leafy.domain.repository.AlarmRepository
import com.ujizin.leafy.domain.result.asResult

class LoadAlarmUseCaseImpl(private val repository: AlarmRepository) : LoadAlarmUseCase {

    override fun invoke(id: Long) = repository.getAlarmById(id).asResult()
}
