package com.eniskaner.onboarding.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.eniskaner.common.preferences.PreferencesManager
import com.eniskaner.eyojnavigation.navigateWithAnimation
import com.eniskaner.feature.onboarding.databinding.FragmentSatelliteSplashBinding
import com.eniskaner.onboarding.navigation.OnBoardingNavGraph
import com.eniskaner.satellitecommunicator.SatelliteFeatureCommunicator
import com.eniskaner.satellitecommunicator.SatelliteListQualifierForSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SatelliteSplashFragment : Fragment() {

    private lateinit var binding: FragmentSatelliteSplashBinding

    @Inject
    lateinit var navController: NavController

    @Inject
    lateinit var preferencesManager: PreferencesManager

    @Inject
    @SatelliteListQualifierForSplashScreen
    lateinit var satelliteFeatureCommunicator: SatelliteFeatureCommunicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSatelliteSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            delay(2000)
            if (preferencesManager.isSatelliteOnBoardingCompleted) {
                satelliteFeatureCommunicator.launchSatelliteFeature(
                    SatelliteFeatureCommunicator.SatelliteFeatureArgs(
                        OnBoardingNavGraph.ROUTE
                    )
                )
                return@launch
            }
            navController.navigateWithAnimation(
                route = OnBoardingNavGraph.VIEW_PAGER_DESTINATION,
                popUpTo = OnBoardingNavGraph.ROUTE,
                inclusive = true
            )
        }
    }
}
