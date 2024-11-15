package com.eniskaner.onboarding.ui.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.eniskaner.common.util.viewBinding
import com.eniskaner.feature.onboarding.R
import com.eniskaner.feature.onboarding.databinding.FragmentSatelliteViewPagerBinding
import com.eniskaner.onboarding.ui.adapter.SatelliteViewPagerAdapter

class SatelliteViewPagerFragment : Fragment(R.layout.fragment_satellite_view_pager) {

    private val binding: FragmentSatelliteViewPagerBinding by viewBinding(
        FragmentSatelliteViewPagerBinding::bind
    )

    private val satelliteScreens by lazy {
        arrayListOf(
            SatelliteFirstScreenFragment(),
            SatelliteSecondScreenFragment(),
            SatelliteThirdScreenFragment()
        )
    }

    private val adapter by lazy {
        SatelliteViewPagerAdapter(
            fragmentList = satelliteScreens,
            fragmentManager = requireActivity().supportFragmentManager,
            lifecycle = lifecycle
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSatelliteViewPagerAdapter()
    }

    private fun setSatelliteViewPagerAdapter() = with(binding) {
        satelliteViewPager.adapter = adapter
    }
}
