package com.eniskaner.satellitedetail.ui.state

import com.eniskaner.satellitedetail.ui.model.SatelliteDetailUIModel

data class SatelliteDetailState(
    val satelliteDetail: SatelliteDetailUIModel? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
