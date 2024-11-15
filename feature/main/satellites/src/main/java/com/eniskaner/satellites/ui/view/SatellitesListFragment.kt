package com.eniskaner.satellites.ui.view

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.eniskaner.common.util.launchAndRepeatWithViewLifecycle
import com.eniskaner.common.util.viewBinding
import com.eniskaner.feature.satellites.R
import com.eniskaner.feature.satellites.databinding.FragmentSatellitesListBinding
import com.eniskaner.satellitecommunicator.SatelliteDetailQualifier
import com.eniskaner.satellitecommunicator.SatelliteFeatureCommunicator
import com.eniskaner.satellites.navigation.SatellitesNavGraph
import com.eniskaner.satellites.ui.adapter.SatelliteClickListener
import com.eniskaner.satellites.ui.adapter.SatelliteListAdapter
import com.eniskaner.satellites.ui.event.SatelliteEvent
import com.eniskaner.satellites.ui.util.SatelliteItemDecoration
import com.eniskaner.satellites.ui.util.SatelliteSearchTextWatcher
import com.eniskaner.satellites.ui.viewmodel.SatellitesSearchViewModel
import com.eniskaner.satellites.ui.viewmodel.SatellitesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SatellitesListFragment : Fragment(R.layout.fragment_satellites_list), SatelliteClickListener {

    private val binding: FragmentSatellitesListBinding by viewBinding(FragmentSatellitesListBinding::bind)

    @Inject
    lateinit var navController: NavController

    @Inject
    @SatelliteDetailQualifier
    lateinit var satelliteDetailCommunicator: SatelliteFeatureCommunicator

    private val satelliteListAdapter by lazy { SatelliteListAdapter(this@SatellitesListFragment) }

    private val satellitesViewModel: SatellitesViewModel by viewModels()

    private val searchViewModel: SatellitesSearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        getSatelliteListData()
        setUIWithSearchSatellites()
    }

    private fun initRecyclerView() = with(binding.rvSatelliteList) {
        adapter = satelliteListAdapter
        layoutManager = LinearLayoutManager(requireContext())
        addItemDecoration(SatelliteItemDecoration())
    }

    private fun getSatelliteListData() {
        launchAndRepeatWithViewLifecycle {
            launch {
                satellitesViewModel.stateListUIState.collect { satellitesListUIState ->
                    setProgressBar(isLoading = satellitesListUIState.isLoading)
                    satelliteListAdapter.submitList(satellitesListUIState.satelliteList)
                }
            }
        }
    }

    private fun setUIWithSearchSatellites() = with(binding) {
        tvSearchSatellite.addTextChangedListener(
            SatelliteSearchTextWatcher { query ->
                searchViewModel.onEvent(SatelliteEvent.SearchSatellites(query = query))
                searchSatellite(query = query)
            }
        )
    }

    private fun searchSatellite(query: String) {
        launchAndRepeatWithViewLifecycle {
            launch {
                searchViewModel.stateSearchUIState.collect { searchUIState ->
                    if (query.isEmpty()) {
                        getSatelliteListData()
                    } else {
                        setProgressBar(isLoading = searchUIState.isLoading)
                        val filteredList = searchViewModel.filterSatellites(query)
                        satelliteListAdapter.submitList(filteredList)
                    }
                }
            }
        }
    }

    private fun setProgressBar(isLoading: Boolean) {
        binding.progressBarSatellites.isVisible = isLoading
    }

    override fun satelliteClicked(id: Int, name: String) {
        satelliteDetailCommunicator.launchSatelliteFeature(
            SatelliteFeatureCommunicator.SatelliteFeatureArgs(
                previousRoute = SatellitesNavGraph.ROUTE,
                satelliteId = id,
                satelliteName = name
            )
        )
    }
}
