package com.ujizin.leafy.core.local

import android.content.Context
import androidx.room.Room
import com.ujizin.leafy.core.local.datasource.AlarmLocalDataSource
import com.ujizin.leafy.core.local.datasource.PlantLocalDataSource
import com.ujizin.leafy.core.local.datasource.UserLocalDataSource
import com.ujizin.leafy.core.local.datastore.UserDataStore
import com.ujizin.leafy.core.local.mapper.AlarmMapper
import com.ujizin.leafy.core.local.mapper.PlantMapper
import com.ujizin.leafy.core.local.mapper.UserMapper
import com.ujizin.leafy.core.repository.datasource.AlarmDataSource
import com.ujizin.leafy.core.repository.datasource.PlantDataSource
import com.ujizin.leafy.core.repository.datasource.UserDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/** Dependency injection of Local Module */
@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): Database =
        Room.databaseBuilder(context, Database::class.java, Database.NAME).build()

    @Singleton
    @Provides
    fun provideMemoryDatabase(@ApplicationContext context: Context): MemoryDatabase =
        Room.inMemoryDatabaseBuilder(context, MemoryDatabase::class.java).build()

    @Singleton
    @Provides
    fun provideLocalPlantDataSource(
        database: Database,
        memoryDatabase: MemoryDatabase,
    ): PlantDataSource =
        PlantLocalDataSource(
            plantDao = database.plantDao(),
            memoryPlantDao = memoryDatabase.plantDao(),
            mapper = PlantMapper(),
        )

    @Singleton
    @Provides
    fun provideLocalAlarmDataSource(database: Database): AlarmDataSource =
        AlarmLocalDataSource(alarmDao = database.alarmDao(), mapper = AlarmMapper())

    @Singleton
    @Provides
    fun provideUserDataSource(userDataStore: UserDataStore): UserDataSource =
        UserLocalDataSource(userDataStore = userDataStore, userMapper = UserMapper())
}
