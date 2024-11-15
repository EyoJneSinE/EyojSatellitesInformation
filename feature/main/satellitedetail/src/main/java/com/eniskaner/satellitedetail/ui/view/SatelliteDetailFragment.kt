package com.eniskaner.satellitedetail.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.eniskaner.common.util.parcelable
import com.eniskaner.satellitecommunicator.SatelliteFeatureCommunicator
import com.eniskaner.satellitedetail.databinding.FragmentSatelliteDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SatelliteDetailFragment : Fragment() {

    lateinit var binding: FragmentSatelliteDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSatelliteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments?.parcelable(SatelliteFeatureCommunicator.SATELLITE_FEATURE_NAV_KEY) as? SatelliteFeatureCommunicator.SatelliteFeatureArgs
        val satelliteId = args?.satelliteId
        binding.tvSatelliteDetail.text = satelliteId.toString()

    }
}