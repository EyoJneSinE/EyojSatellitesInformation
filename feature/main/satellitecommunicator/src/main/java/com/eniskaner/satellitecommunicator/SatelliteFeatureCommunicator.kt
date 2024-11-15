package com.eniskaner.satellitecommunicator

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

interface SatelliteFeatureCommunicator {

    fun launchSatelliteFeature(satelliteFeatureArgs: SatelliteFeatureArgs)

    companion object {
        const val SATELLITE_FEATURE_NAV_KEY = "satelliteFeatureNavKey"
    }

    @Parcelize
    data class SatelliteFeatureArgs(
        val previousRoute: String,
        val satelliteId: Int? = null,
        val satelliteName: String? = null
    ) : Parcelable
}
