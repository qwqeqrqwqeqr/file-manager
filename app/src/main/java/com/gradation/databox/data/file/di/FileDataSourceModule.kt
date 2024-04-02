package com.gradation.databox.data.file.di

import com.gradation.databox.data.file.datasource.DefaultFileDataSource
import com.gradation.databox.data.file.datasource.FileDataSource
import com.gradation.databox.di.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object FileDataSourceModule {

    @Singleton
    @Provides
    fun provideFileDataSource(
        dispatcherProvider: DispatcherProvider
    ): FileDataSource = DefaultFileDataSource(dispatcherProvider)
}


