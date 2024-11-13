package com.eniskaner.satellites.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.fragment.fragment
import androidx.navigation.navigation
import com.eniskaner.eyojnavigation.NavigationGraph
import com.eniskaner.satellites.ui.view.SatellitesListFragment
import javax.inject.Inject

class SatellitesNavGraph @Inject constructor() : NavigationGraph {

    override fun addNav(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.apply {
            navigation(startDestination = START_DESTINATION, route = ROUTE) {
                fragment<SatellitesListFragment>(START_DESTINATION)
            }
        }
    }

    companion object {
        const val ROUTE = "satellites_route"
        const val START_DESTINATION = "satellite_list_screen"
    }
}
