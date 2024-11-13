package com.eniskaner.satellites.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.eniskaner.feature.satellites.databinding.FragmentSatellitesListBinding
import com.eniskaner.satellitecommunicator.SatelliteDetailQualifier
import com.eniskaner.satellitecommunicator.SatelliteFeatureCommunicator
import com.eniskaner.satellites.navigation.SatellitesNavGraph
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SatellitesListFragment : Fragment() {

    private lateinit var binding: FragmentSatellitesListBinding

    @Inject
    lateinit var navController: NavController

    @Inject
    @SatelliteDetailQualifier
    lateinit var satelliteDetailCommunicator: SatelliteFeatureCommunicator


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSatellitesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.satelliteListFragmentTitle.setOnClickListener {
            val satelliteId = 123
            satelliteDetailCommunicator.launchSatelliteFeature(
                SatelliteFeatureCommunicator.SatelliteFeatureArgs(
                    previousRoute = SatellitesNavGraph.ROUTE,
                    satelliteId = satelliteId
                )
            )
        }
    }
}
