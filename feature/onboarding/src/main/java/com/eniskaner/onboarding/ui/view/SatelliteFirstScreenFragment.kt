package com.eniskaner.onboarding.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.eniskaner.common.util.viewBinding
import com.eniskaner.feature.onboarding.R
import com.eniskaner.feature.onboarding.databinding.FragmentSatelliteFirstScreenBinding
import com.eniskaner.onboarding.ui.util.updateViewPagerIndex

class SatelliteFirstScreenFragment : Fragment(R.layout.fragment_satellite_first_screen) {

    private val binding: FragmentSatelliteFirstScreenBinding by viewBinding(FragmentSatelliteFirstScreenBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButton()
    }

    private fun setButton() = with(binding) {
        bNextSatelliteFirstScreen.setOnClickListener {
            updateViewPagerIndex(increment = true)
        }
    }
}
