package com.gradation.databox.core.common.event

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object EventManagerModule {

    @Singleton
    @Provides
    fun provideEventManager(): EventManager = EventManager()
}


