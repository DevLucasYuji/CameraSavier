package com.ujizin.leafy.domain.usecase.alarm.delete

import com.ujizin.leafy.domain.repository.AlarmRepository

class DeleteAlarmUseCaseImpl(private val repository: AlarmRepository) : DeleteAlarmUseCase {

    override fun invoke(plantId: Long) = repository.deleteAlarmByPlantId(plantId)
}
