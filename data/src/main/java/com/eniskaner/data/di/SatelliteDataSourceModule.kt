package com.eniskaner.data.di

import com.eniskaner.data.datasource.SatelliteDataSource
import com.eniskaner.data.datasource.SatelliteDataSourceImpl
import com.eniskaner.data.datasource.SatelliteLocalDataSource
import com.eniskaner.data.datasource.SatelliteLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SatelliteDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindSatelliteDataSource(satelliteDataSourceImpl: SatelliteDataSourceImpl): SatelliteDataSource

    @Binds
    @Singleton
    abstract fun bindSatelliteDetailDataSource(satelliteLocalDataSourceImpl: SatelliteLocalDataSourceImpl): SatelliteLocalDataSource
}