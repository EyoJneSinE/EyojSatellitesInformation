package com.eniskaner.onboarding.ui.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.eniskaner.common.preferences.PreferencesManager
import com.eniskaner.common.util.launchAndRepeatWithViewLifecycle
import com.eniskaner.common.util.viewBinding
import com.eniskaner.eyojnavigation.navigateWithAnimation
import com.eniskaner.feature.onboarding.R
import com.eniskaner.feature.onboarding.databinding.FragmentSatelliteSplashBinding
import com.eniskaner.onboarding.navigation.OnBoardingNavGraph
import com.eniskaner.onboarding.ui.util.addAnimatorEndListener
import com.eniskaner.satellitecommunicator.SatelliteFeatureCommunicator
import com.eniskaner.satellitecommunicator.SatelliteListQualifierForSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SatelliteSplashFragment : Fragment(R.layout.fragment_satellite_splash) {

    private val binding: FragmentSatelliteSplashBinding by viewBinding(
        FragmentSatelliteSplashBinding::bind
    )

    @Inject
    lateinit var navController: NavController

    @Inject
    lateinit var preferencesManager: PreferencesManager

    @Inject
    @SatelliteListQualifierForSplashScreen
    lateinit var satelliteFeatureCommunicator: SatelliteFeatureCommunicator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAnimatorEndListener()
    }

    private fun setAnimatorEndListener() {
        launchAndRepeatWithViewLifecycle {
            launch {
                binding.animationView.addAnimatorEndListener {
                    navigateToNextScreen()
                }
            }
        }
    }

    private fun navigateToNextScreen() {
        if (preferencesManager.isSatelliteOnBoardingCompleted) {
            satelliteFeatureCommunicator.launchSatelliteFeature(
                SatelliteFeatureCommunicator.SatelliteFeatureArgs(
                    OnBoardingNavGraph.ROUTE
                )
            )
        } else {
            navController.navigateWithAnimation(
                route = OnBoardingNavGraph.VIEW_PAGER_DESTINATION,
                popUpTo = OnBoardingNavGraph.ROUTE,
                inclusive = true
            )
        }
    }
}
