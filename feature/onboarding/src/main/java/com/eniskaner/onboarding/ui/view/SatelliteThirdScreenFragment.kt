package com.eniskaner.onboarding.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.eniskaner.common.preferences.PreferencesManager
import com.eniskaner.common.util.viewBinding
import com.eniskaner.feature.onboarding.R
import com.eniskaner.feature.onboarding.databinding.FragmentSatelliteThirdScreenBinding
import com.eniskaner.onboarding.navigation.OnBoardingNavGraph
import com.eniskaner.onboarding.ui.util.updateViewPagerIndex
import com.eniskaner.satellitecommunicator.SatelliteFeatureCommunicator
import com.eniskaner.satellitecommunicator.SatellitesListQualifierForOnBoardingScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SatelliteThirdScreenFragment : Fragment(R.layout.fragment_satellite_third_screen) {

    private val binding: FragmentSatelliteThirdScreenBinding by viewBinding(FragmentSatelliteThirdScreenBinding::bind)

    @Inject
    lateinit var preferencesManager: PreferencesManager

    @Inject
    @SatellitesListQualifierForOnBoardingScreen
    lateinit var satelliteFeatureCommunicator: SatelliteFeatureCommunicator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButtons()
    }

    private fun setButtons() = with(binding) {
        bFinishSatelliteThirdScreen.setOnClickListener {
            preferencesManager.isSatelliteOnBoardingCompleted = true
            satelliteFeatureCommunicator.launchSatelliteFeature(
                SatelliteFeatureCommunicator.SatelliteFeatureArgs(
                    OnBoardingNavGraph.ROUTE
                )
            )
        }
        bPreviousSatelliteThirdScreen.setOnClickListener {
            updateViewPagerIndex(increment = false)
        }
    }
}
