package com.eniskaner.satellitedetail.navigation.di

import com.eniskaner.eyojnavigation.NavigationGraph
import com.eniskaner.satellitedetail.navigation.SatelliteDetailNavGraph
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
interface SatelliteDetailNavigationModule {
    @IntoSet
    @Binds
    fun bindSatelliteDetailNavigation(satelliteDetailNavGraph: SatelliteDetailNavGraph): NavigationGraph
}