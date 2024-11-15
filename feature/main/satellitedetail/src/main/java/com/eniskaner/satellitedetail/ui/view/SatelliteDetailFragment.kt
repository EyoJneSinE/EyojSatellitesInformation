package com.eniskaner.satellitedetail.ui.view

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.eniskaner.common.util.launchAndRepeatWithViewLifecycle
import com.eniskaner.common.util.parcelable
import com.eniskaner.common.util.viewBinding
import com.eniskaner.satellitecommunicator.SatelliteFeatureCommunicator
import com.eniskaner.satellitedetail.R
import com.eniskaner.satellitedetail.databinding.FragmentSatelliteDetailBinding
import com.eniskaner.satellitedetail.ui.viewmodel.SatelliteDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SatelliteDetailFragment : Fragment(R.layout.fragment_satellite_detail) {

    private val binding: FragmentSatelliteDetailBinding by viewBinding(
        FragmentSatelliteDetailBinding::bind
    )

    private val satelliteDetailViewModel: SatelliteDetailViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args =
            arguments?.parcelable(SatelliteFeatureCommunicator.SATELLITE_FEATURE_NAV_KEY) as? SatelliteFeatureCommunicator.SatelliteFeatureArgs
        val satelliteId = args?.satelliteId
        val satelliteName = args?.satelliteName
        if (satelliteId != null && satelliteName != null) {
            getSatelliteDetailData(satelliteId, satelliteName)
        }
        observeLoadingState()
        observeSatelliteDetailData()
    }

    private fun getSatelliteDetailData(satelliteId: Int, satelliteName: String) {
        satelliteDetailViewModel.getSatelliteDetail(satelliteId, satelliteName)
    }

    private fun observeSatelliteDetailData() {
        launchAndRepeatWithViewLifecycle {
            launch {
                satelliteDetailViewModel.satelliteDetail.collect { satelliteDetailState ->
                    val satelliteDetail = satelliteDetailState.satelliteDetail
                    with(binding) {
                        tvSatelliteName.text =
                            getString(R.string.satellite_name_placeholder, satelliteDetail?.name)
                        tvLaunchDate.text = getString(
                            R.string.launch_date_placeholder,
                            satelliteDetail?.firstFlight
                        )
                        tvHeightMassValue.text = getString(
                            R.string.height_mass_placeholder,
                            satelliteDetail?.height,
                            satelliteDetail?.mass
                        )
                        tvCostValue.text =
                            getString(R.string.cost_placeholder, satelliteDetail?.costPerLaunch)
                        tvLastPositionValue.text = getString(
                            R.string.last_position_placeholder,
                            satelliteDetail?.posX,
                            satelliteDetail?.posY
                        )
                    }
                }
            }
        }
    }

    private fun observeLoadingState() {
        launchAndRepeatWithViewLifecycle {
            launch {
                satelliteDetailViewModel.satelliteDetail.collect { satelliteDetailState ->
                    setProgressBarVisibility(satelliteDetailState.isLoading)
                }
            }
        }
    }

    private fun setProgressBarVisibility(isLoading: Boolean) {
        binding.progressBarSatellites.isVisible = isLoading
    }
}
