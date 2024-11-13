package com.eniskaner.onboarding.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.eniskaner.common.preferences.PreferencesManager
import com.eniskaner.feature.onboarding.R
import com.eniskaner.feature.onboarding.databinding.FragmentSatelliteThirdScreenBinding
import com.eniskaner.onboarding.navigation.OnBoardingNavGraph
import com.eniskaner.satellitecommunicator.SatelliteFeatureCommunicator
import com.eniskaner.satellitecommunicator.SatellitesListQualifierForOnBoardingScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SatelliteThirdScreenFragment : Fragment() {

    private lateinit var binding: FragmentSatelliteThirdScreenBinding

    @Inject
    lateinit var preferencesManager: PreferencesManager

    @Inject
    @SatellitesListQualifierForOnBoardingScreen
    lateinit var satelliteFeatureCommunicator: SatelliteFeatureCommunicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSatelliteThirdScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

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
            activity?.findViewById<ViewPager2>(R.id.satellite_view_pager)?.apply {
                currentItem = --currentItem
            }
        }
    }
}
