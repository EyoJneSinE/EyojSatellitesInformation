package com.eniskaner.satellites.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.eniskaner.common.util.launchAndRepeatWithViewLifecycle
import com.eniskaner.feature.satellites.databinding.FragmentSatellitesListBinding
import com.eniskaner.satellitecommunicator.SatelliteDetailQualifier
import com.eniskaner.satellitecommunicator.SatelliteFeatureCommunicator
import com.eniskaner.satellites.navigation.SatellitesNavGraph
import com.eniskaner.satellites.ui.adapter.SatelliteClickListener
import com.eniskaner.satellites.ui.adapter.SatelliteListAdapter
import com.eniskaner.satellites.ui.viewmodel.SatellitesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SatellitesListFragment : Fragment(), SatelliteClickListener {

    private lateinit var binding: FragmentSatellitesListBinding

    @Inject
    lateinit var navController: NavController

    @Inject
    @SatelliteDetailQualifier
    lateinit var satelliteDetailCommunicator: SatelliteFeatureCommunicator

    private val satelliteListAdapter by lazy { SatelliteListAdapter(this@SatellitesListFragment) }

    private val satellitesViewModel: SatellitesViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSatellitesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        getSatelliteListData()
    }

    private fun initRecyclerView() {
        binding.rvSatelliteList.adapter = satelliteListAdapter
        binding.rvSatelliteList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun getSatelliteListData() {
        launchAndRepeatWithViewLifecycle {
            launch {
                satellitesViewModel.stateListUIState.collect {satellitesListUIState ->
                    satelliteListAdapter.submitList(satellitesListUIState.satelliteList)
                }
            }
        }
    }

    override fun satelliteClicked(id: Int) {
        satelliteDetailCommunicator.launchSatelliteFeature(
            SatelliteFeatureCommunicator.SatelliteFeatureArgs(
                previousRoute = SatellitesNavGraph.ROUTE,
                satelliteId = id
            )
        )
    }


}
