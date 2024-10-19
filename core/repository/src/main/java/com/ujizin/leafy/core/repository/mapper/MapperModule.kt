package com.ujizin.leafy.core.repository.mapper

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object MapperModule {

    @Provides @Singleton fun providePlantMapper() = PlantMapper()

    @Provides @Singleton fun provideAlarmMapper() = AlarmMapper()

    @Provides @Singleton fun provideUserMapper() = UserMapper()
}
