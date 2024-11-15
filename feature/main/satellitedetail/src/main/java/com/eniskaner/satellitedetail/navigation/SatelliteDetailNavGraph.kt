package com.eniskaner.satellitedetail.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.fragment.fragment
import androidx.navigation.navigation
import com.eniskaner.eyojnavigation.NavigationGraph
import com.eniskaner.satellitedetail.ui.view.SatelliteDetailFragment
import javax.inject.Inject

class SatelliteDetailNavGraph @Inject constructor() : NavigationGraph {

    override fun addNav(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.apply {
            navigation(startDestination = START_DESTINATION, route = ROUTE) {
                fragment<SatelliteDetailFragment>(START_DESTINATION)
            }
        }
    }

    companion object {
        const val ROUTE = "satellite_detail_route"
        const val START_DESTINATION = "satellite_detail_screen"
    }
}
