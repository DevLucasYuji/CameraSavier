package com.ujizin.leafy.core.repository

import com.ujizin.leafy.core.repository.implementation.AlarmRepositoryImpl
import com.ujizin.leafy.core.repository.implementation.FileRepositoryImpl
import com.ujizin.leafy.core.repository.implementation.PlantRepositoryImpl
import com.ujizin.leafy.core.repository.implementation.RingtoneRepositoryImpl
import com.ujizin.leafy.core.repository.implementation.UserRepositoryImpl
import com.ujizin.leafy.domain.repository.AlarmRepository
import com.ujizin.leafy.domain.repository.FileRepository
import com.ujizin.leafy.domain.repository.PlantRepository
import com.ujizin.leafy.domain.repository.RingtoneRepository
import com.ujizin.leafy.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/** Dependency injection of repository module */
@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Binds fun providePlantRepository(plantRepositoryImpl: PlantRepositoryImpl): PlantRepository

    @Binds fun provideAlarmRepository(alarmRepositoryImpl: AlarmRepositoryImpl): AlarmRepository

    @Binds fun provideUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds fun provideFileRepository(fileRepositoryImpl: FileRepositoryImpl): FileRepository

    @Binds
    fun provideRingtoneRepository(
        ringtoneRepositoryImpl: RingtoneRepositoryImpl
    ): RingtoneRepository
}
