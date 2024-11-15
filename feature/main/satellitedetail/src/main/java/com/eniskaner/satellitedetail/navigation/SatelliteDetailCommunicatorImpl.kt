package com.eniskaner.satellitedetail.navigation

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.eniskaner.eyojnavigation.navigateWithAnimation
import com.eniskaner.satellitecommunicator.SatelliteFeatureCommunicator
import javax.inject.Inject

class SatelliteDetailCommunicatorImpl @Inject constructor(
    private val navController: NavController
): SatelliteFeatureCommunicator {

    override fun launchSatelliteFeature(satelliteDetailArgs: SatelliteFeatureCommunicator.SatelliteFeatureArgs) {
        navController.navigateWithAnimation(
            route = SatelliteDetailNavGraph.ROUTE,
            args = bundleOf(SatelliteFeatureCommunicator.SATELLITE_FEATURE_NAV_KEY to satelliteDetailArgs),
        )
    }
}
