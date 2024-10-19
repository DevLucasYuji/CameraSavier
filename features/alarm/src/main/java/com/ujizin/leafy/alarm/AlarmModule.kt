package com.ujizin.leafy.alarm

import android.content.Context
import com.ujizin.leafy.alarm.notificator.AlarmNotificator
import com.ujizin.leafy.alarm.notificator.AlarmNotificatorImpl
import com.ujizin.leafy.alarm.scheduler.AlarmScheduler
import com.ujizin.leafy.alarm.scheduler.AlarmSchedulerImpl
import com.ujizin.leafy.alarm.usecase.SchedulePlantAlarmUseCase
import com.ujizin.leafy.domain.usecase.alarm.load.LoadAlarmUseCase
import com.ujizin.leafy.domain.usecase.plant.load.LoadPlantUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AlarmModule {

    @Provides
    @Singleton
    fun provideAlarmScheduler(@ApplicationContext context: Context): AlarmScheduler =
        AlarmSchedulerImpl(context)

    @Provides
    @Singleton
    fun provideShowAlarm(
        loadPlant: LoadPlantUseCase,
        loadAlarm: LoadAlarmUseCase,
        alarmScheduler: AlarmScheduler,
    ) = SchedulePlantAlarmUseCase(loadPlant, loadAlarm, alarmScheduler)

    @Provides
    @Singleton
    fun provideAlarmNotificator(@ApplicationContext context: Context): AlarmNotificator =
        AlarmNotificatorImpl(context)
}
