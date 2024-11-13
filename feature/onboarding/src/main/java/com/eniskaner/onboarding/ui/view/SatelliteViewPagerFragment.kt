package com.eniskaner.onboarding.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.eniskaner.feature.onboarding.databinding.FragmentSatelliteViewPagerBinding
import com.eniskaner.onboarding.ui.adapter.SatelliteViewPagerAdapter

class SatelliteViewPagerFragment : Fragment() {

    private lateinit var binding: FragmentSatelliteViewPagerBinding

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSatelliteViewPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSatelliteViewPagerAdapter()
    }

    private fun setSatelliteViewPagerAdapter() = with(binding) {
        satelliteViewPager.adapter = adapter
    }
}
