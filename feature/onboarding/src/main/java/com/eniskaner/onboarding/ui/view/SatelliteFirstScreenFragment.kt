package com.eniskaner.onboarding.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.eniskaner.feature.onboarding.R
import com.eniskaner.feature.onboarding.databinding.FragmentSatelliteFirstScreenBinding

class SatelliteFirstScreenFragment : Fragment() {

    private lateinit var binding: FragmentSatelliteFirstScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSatelliteFirstScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButton()
    }

    private fun setButton() = with(binding) {
        bSatelliteFirstScreen.setOnClickListener {
            activity?.findViewById<ViewPager2>(R.id.satellite_view_pager)?.apply {
                currentItem = ++currentItem
            }
        }
    }
}
