package br.com.devlucasyuji.domain.usecase.plant

import br.com.devlucasyuji.domain.repository.PlantRepository
import br.com.devlucasyuji.domain.usecase.plant.implementation.AddDraftPlantImpl
import br.com.devlucasyuji.domain.usecase.plant.implementation.AddPlantImpl
import br.com.devlucasyuji.domain.usecase.plant.implementation.DeletePlantImpl
import br.com.devlucasyuji.domain.usecase.plant.implementation.FindPlantImpl
import br.com.devlucasyuji.domain.usecase.plant.implementation.LoadAllPlantImpl
import br.com.devlucasyuji.domain.usecase.plant.implementation.LoadDraftPlantImpl
import br.com.devlucasyuji.domain.usecase.plant.implementation.LoadPlantImpl
import br.com.devlucasyuji.domain.usecase.plant.implementation.UpdatePlantImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PlantModule {

    @Provides
    @Singleton
    fun provideLoadAllPlants(
        repository: PlantRepository
    ): LoadAllPlant = LoadAllPlantImpl(repository)

    @Provides
    @Singleton
    fun provideLoadPlant(
        repository: PlantRepository
    ): LoadPlant = LoadPlantImpl(repository)

    @Provides
    @Singleton
    fun provideAddPlant(
        repository: PlantRepository
    ): AddPlant = AddPlantImpl(repository)

    @Provides
    @Singleton
    fun provideFindPlant(
        repository: PlantRepository
    ): FindPlant = FindPlantImpl(repository)

    @Provides
    @Singleton
    fun provideUpdatePlant(
        repository: PlantRepository
    ): UpdatePlant = UpdatePlantImpl(repository)

    @Provides
    @Singleton
    fun provideDeletePlant(
        repository: PlantRepository
    ): DeletePlant = DeletePlantImpl(repository)

    @Provides
    @Singleton
    fun provideAddDraftPlant(
        repository: PlantRepository
    ): AddDraftPlant = AddDraftPlantImpl(repository)

    @Provides
    @Singleton
    fun provideLoadDraftPlant(
        repository: PlantRepository
    ): LoadDraftPlant = LoadDraftPlantImpl(repository)
}