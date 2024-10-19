package com.ujizin.leafy.core.local.datasource

import com.ujizin.leafy.core.local.dao.AlarmDao
import com.ujizin.leafy.core.local.mapper.AlarmMapper
import com.ujizin.leafy.core.repository.datasource.AlarmDataSource
import com.ujizin.leafy.core.repository.model.Alarm

internal class AlarmLocalDataSource(
    private val alarmDao: AlarmDao,
    private val mapper: AlarmMapper,
) : AlarmDataSource {

    override suspend fun insertAlarm(alarm: Alarm) = alarmDao.insert(mapper.toAlarmEntity(alarm))

    override suspend fun insertAlarms(alarms: List<Alarm>) {
        val alarmEntities = alarms.map { mapper.toAlarmEntity(it) }
        alarmEntities.forEach { alarmEntity -> alarmDao.insert(alarmEntity) }
    }

    override suspend fun getAlarms(): List<Alarm> = alarmDao.getAll().map(mapper::toAlarm)

    override suspend fun getAlarmsByPlantId(plantId: Long) =
        alarmDao.getByPlantId(plantId).map(mapper::toAlarm)

    override suspend fun getAlarmById(id: Long) = alarmDao.findAlarmById(id).let(mapper::toAlarm)

    override suspend fun updateAlarm(alarm: Alarm) {
        alarmDao.update(mapper.toAlarmEntity(alarm))
    }

    override suspend fun deleteAlarm(alarm: Alarm) {
        alarmDao.delete(mapper.toAlarmEntity(alarm))
    }

    override suspend fun deleteAlarmByPlantId(plantId: Long) {
        alarmDao.deleteAlarmByPlantId(plantId)
    }
}
