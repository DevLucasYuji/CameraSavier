package com.ujizin.leafy.domain.usecase.file

import com.ujizin.leafy.domain.repository.FileRepository
import com.ujizin.leafy.domain.usecase.file.save.SaveFileUseCase
import com.ujizin.leafy.domain.usecase.file.save.SaveFileUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FileModule {

    @Provides
    @Singleton
    fun provideSaveFile(repository: FileRepository): SaveFileUseCase =
        SaveFileUseCaseImpl(repository)
}
