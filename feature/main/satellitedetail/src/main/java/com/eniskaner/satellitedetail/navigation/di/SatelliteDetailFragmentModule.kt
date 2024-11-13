package com.eniskaner.satellitedetail.navigation.di

import com.eniskaner.satellitecommunicator.SatelliteDetailQualifier
import com.eniskaner.satellitecommunicator.SatelliteFeatureCommunicator
import com.eniskaner.satellitedetail.navigation.SatelliteDetailCommunicatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Qualifier

@Module
@InstallIn(FragmentComponent::class)
interface SatelliteDetailFragmentModule {

    @Binds
    @SatelliteDetailQualifier
    fun bindSatelliteDetailCommunicator(
        satelliteDetailCommunicatorImpl: SatelliteDetailCommunicatorImpl
    ): SatelliteFeatureCommunicator
}