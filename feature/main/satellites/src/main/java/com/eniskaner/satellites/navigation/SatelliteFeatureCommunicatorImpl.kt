package com.eniskaner.satellites.navigation

import androidx.navigation.NavController
import com.eniskaner.eyojnavigation.navigateWithAnimation
import com.eniskaner.satellitecommunicator.SatelliteFeatureCommunicator
import javax.inject.Inject

class SatelliteFeatureCommunicatorImpl @Inject constructor(
    private val navController: NavController
) : SatelliteFeatureCommunicator {

    override fun launchSatelliteFeature(satelliteFeatureArgs: SatelliteFeatureCommunicator.SatelliteFeatureArgs) {
        navController.navigateWithAnimation(
            route = SatellitesNavGraph.ROUTE,
            popUpTo = satelliteFeatureArgs.previousRoute,
            inclusive = true
        )
    }
}
