package com.ujizin.leafy.domain.usecase.user

import com.ujizin.leafy.domain.repository.UserRepository
import com.ujizin.leafy.domain.usecase.user.load.LoadUserUseCase
import com.ujizin.leafy.domain.usecase.user.load.LoadUserUseCaseImpl
import com.ujizin.leafy.domain.usecase.user.update.UpdateUserUseCase
import com.ujizin.leafy.domain.usecase.user.update.UpdateUserUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    @Provides
    @Singleton
    fun provideLoadUser(repository: UserRepository): LoadUserUseCase =
        LoadUserUseCaseImpl(repository)

    @Provides
    @Singleton
    fun provideUpdateUser(repository: UserRepository): UpdateUserUseCase =
        UpdateUserUseCaseImpl(repository)
}
