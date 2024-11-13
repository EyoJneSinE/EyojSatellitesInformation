package com.eniskaner.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PositionListDTO(
    val list: List<SatellitePositionDTO>
)
