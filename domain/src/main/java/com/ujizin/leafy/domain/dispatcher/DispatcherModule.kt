package com.ujizin.leafy.domain.dispatcher

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @Provides @IoDispatcher fun provideIoDispatcher() = Dispatchers.IO

    @Provides @DefaultDispatcher fun provideDefaultDispatcher() = Dispatchers.Default
}
