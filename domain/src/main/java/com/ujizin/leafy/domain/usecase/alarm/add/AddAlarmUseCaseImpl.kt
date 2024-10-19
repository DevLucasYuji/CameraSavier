package com.ujizin.leafy.domain.usecase.alarm.add

import com.ujizin.leafy.domain.model.Alarm
import com.ujizin.leafy.domain.repository.AlarmRepository

internal class AddAlarmUseCaseImpl(private val alarmRepository: AlarmRepository) : AddAlarmUseCase {

    override fun invoke(alarm: Alarm) = alarmRepository.insertAlarm(alarm)
}
