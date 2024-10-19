package com.ujizin.leafy.domain.usecase.plant

import com.ujizin.leafy.domain.repository.PlantRepository
import com.ujizin.leafy.domain.usecase.plant.add.AddDraftPlantUseCase
import com.ujizin.leafy.domain.usecase.plant.add.AddDraftPlantUseCaseImpl
import com.ujizin.leafy.domain.usecase.plant.add.AddPlantUseCase
import com.ujizin.leafy.domain.usecase.plant.add.AddPlantUseCaseImpl
import com.ujizin.leafy.domain.usecase.plant.delete.DeletePlantUseCase
import com.ujizin.leafy.domain.usecase.plant.delete.DeletePlantUseCaseImpl
import com.ujizin.leafy.domain.usecase.plant.find.FindPlantUseCase
import com.ujizin.leafy.domain.usecase.plant.find.FindPlantUseCaseImpl
import com.ujizin.leafy.domain.usecase.plant.load.LoadAllPlantUseCase
import com.ujizin.leafy.domain.usecase.plant.load.LoadAllPlantUseCaseImpl
import com.ujizin.leafy.domain.usecase.plant.load.LoadDraftPlantUseCase
import com.ujizin.leafy.domain.usecase.plant.load.LoadDraftPlantUseCaseImpl
import com.ujizin.leafy.domain.usecase.plant.load.LoadPlantUseCase
import com.ujizin.leafy.domain.usecase.plant.load.LoadPlantUseCaseImpl
import com.ujizin.leafy.domain.usecase.plant.update.UpdatePlant
import com.ujizin.leafy.domain.usecase.plant.update.UpdatePlantImpl
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
    fun provideLoadAllPlants(repository: PlantRepository): LoadAllPlantUseCase =
        LoadAllPlantUseCaseImpl(repository)

    @Provides
    @Singleton
    fun provideLoadPlant(repository: PlantRepository): LoadPlantUseCase =
        LoadPlantUseCaseImpl(repository)

    @Provides
    @Singleton
    fun provideAddPlant(repository: PlantRepository): AddPlantUseCase =
        AddPlantUseCaseImpl(repository)

    @Provides
    @Singleton
    fun provideFindPlant(repository: PlantRepository): FindPlantUseCase =
        FindPlantUseCaseImpl(repository)

    @Provides
    @Singleton
    fun provideUpdatePlant(repository: PlantRepository): UpdatePlant = UpdatePlantImpl(repository)

    @Provides
    @Singleton
    fun provideDeletePlant(repository: PlantRepository): DeletePlantUseCase =
        DeletePlantUseCaseImpl(repository)

    @Provides
    @Singleton
    fun provideAddDraftPlant(repository: PlantRepository): AddDraftPlantUseCase =
        AddDraftPlantUseCaseImpl(repository)

    @Provides
    @Singleton
    fun provideLoadDraftPlant(repository: PlantRepository): LoadDraftPlantUseCase =
        LoadDraftPlantUseCaseImpl(repository)
}
