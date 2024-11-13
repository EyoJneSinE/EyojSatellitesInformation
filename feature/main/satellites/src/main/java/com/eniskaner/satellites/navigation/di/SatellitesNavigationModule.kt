package com.eniskaner.satellites.navigation.di

import com.eniskaner.eyojnavigation.NavigationGraph
import com.eniskaner.satellites.navigation.SatellitesNavGraph
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
interface SatellitesNavigationModule {

    @IntoSet
    @Binds
    fun bindSatellitesNavigation(satellitesNavGraph: SatellitesNavGraph): NavigationGraph
}
