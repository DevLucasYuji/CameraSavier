package com.ujizin.leafy.domain.usecase.alarm.update

import com.ujizin.leafy.domain.model.Alarm
import com.ujizin.leafy.domain.repository.AlarmRepository

class UpdateAlarmUseCaseImpl(private val repository: AlarmRepository) : UpdateAlarmUseCase {

    override fun invoke(alarm: Alarm) = repository.updateAlarm(alarm)
}
