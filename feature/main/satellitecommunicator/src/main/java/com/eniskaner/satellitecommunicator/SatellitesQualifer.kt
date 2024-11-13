package com.eniskaner.satellitecommunicator

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SatellitesListQualifierForOnBoardingScreen

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SatelliteDetailQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SatelliteListQualifierForSplashScreen
