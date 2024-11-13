package com.eniskaner.onboarding.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.fragment.fragment
import androidx.navigation.navigation
import com.eniskaner.eyojnavigation.NavigationGraph
import com.eniskaner.onboarding.ui.view.SatelliteSplashFragment
import com.eniskaner.onboarding.ui.view.SatelliteViewPagerFragment
import javax.inject.Inject

class OnBoardingNavGraph @Inject constructor() : NavigationGraph {

    override fun addNav(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.apply {
            navigation(startDestination = START_DESTINATION, route = ROUTE) {
                fragment<SatelliteSplashFragment>(START_DESTINATION)
                fragment<SatelliteViewPagerFragment>(VIEW_PAGER_DESTINATION)
            }
        }
    }

    companion object {
        const val ROUTE = "onboarding_route"
        const val START_DESTINATION = "satellite_splash_screen"
        const val VIEW_PAGER_DESTINATION = "satellite_view_pager_screen"
    }
}
