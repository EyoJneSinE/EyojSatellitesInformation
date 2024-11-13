package com.eniskaner.satellites.navigation.di

import com.eniskaner.satellitecommunicator.SatelliteFeatureCommunicator
import com.eniskaner.satellitecommunicator.SatelliteListQualifierForSplashScreen
import com.eniskaner.satellitecommunicator.SatellitesListQualifierForOnBoardingScreen
import com.eniskaner.satellites.navigation.SatelliteFeatureCommunicatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
interface SatellitesFragmentModule {

    @Binds
    @SatellitesListQualifierForOnBoardingScreen
    fun bindSatelliteListFragmentCommunicatorWithOnBoardingScreen(
        satellitesFeatureCommunicatorImpl: SatelliteFeatureCommunicatorImpl
    ): SatelliteFeatureCommunicator

    @Binds
    @SatelliteListQualifierForSplashScreen
    fun bindSatelliteListFragmentCommunicatorWithSplashScreen(
        satellitesFeatureCommunicatorImpl: SatelliteFeatureCommunicatorImpl
    ): SatelliteFeatureCommunicator
}
