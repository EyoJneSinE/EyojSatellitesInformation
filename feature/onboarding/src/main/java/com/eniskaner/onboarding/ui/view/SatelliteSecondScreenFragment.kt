package com.eniskaner.onboarding.ui.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.eniskaner.common.util.viewBinding
import com.eniskaner.feature.onboarding.R
import com.eniskaner.feature.onboarding.databinding.FragmentSatelliteSecondScreenBinding
import com.eniskaner.onboarding.ui.util.updateViewPagerIndex

class SatelliteSecondScreenFragment : Fragment(R.layout.fragment_satellite_second_screen) {

    private val binding: FragmentSatelliteSecondScreenBinding by viewBinding(FragmentSatelliteSecondScreenBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButtons()
    }

    private fun setButtons() = with(binding) {
        bNextSatelliteSecondScreen.setOnClickListener {
            updateViewPagerIndex(increment = true)
        }
        bPreviousSatelliteSecondScreen.setOnClickListener {
            updateViewPagerIndex(increment = false)
        }
    }
}
