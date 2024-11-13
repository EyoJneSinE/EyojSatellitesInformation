package com.eniskaner.data.di

import com.eniskaner.data.repository.SatelliteRepositoryImpl
import com.eniskaner.domain.repository.SatelliteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SatelliteRepositoryModule {

    @Binds
    abstract fun bindSatelliteRepository(satelliteRepositoryImpl: SatelliteRepositoryImpl): SatelliteRepository
}