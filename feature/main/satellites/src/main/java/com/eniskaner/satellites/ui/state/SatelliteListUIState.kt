package com.eniskaner.satellites.ui.state

import com.eniskaner.domain.model.SatelliteUI

data class SatelliteListUIState(
    val satelliteList: List<SatelliteUI> = emptyList(),
    val searchingSatelliteList: List<SatelliteUI> = emptyList(),
    val isLoading: Boolean = false,
    val query: String = "",
    val error: String? = null
)
