package com.eniskaner.onboarding.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.eniskaner.common.util.viewBinding
import com.eniskaner.feature.onboarding.R
import com.eniskaner.feature.onboarding.databinding.FragmentSatelliteSecondScreenBinding

class SatelliteSecondScreenFragment : Fragment(R.layout.fragment_satellite_second_screen) {

    private val binding: FragmentSatelliteSecondScreenBinding by viewBinding(FragmentSatelliteSecondScreenBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButtons()
    }

    private fun setButtons() = with(binding) {
        bNextSatelliteSecondScreen.setOnClickListener {
            activity?.findViewById<ViewPager2>(R.id.satellite_view_pager)?.apply {
                currentItem = ++currentItem
            }
        }
        bPreviousSatelliteSecondScreen.setOnClickListener {
            activity?.findViewById<ViewPager2>(R.id.satellite_view_pager)?.apply {
                currentItem = --currentItem
            }
        }
    }
}
