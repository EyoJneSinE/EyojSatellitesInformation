package com.eniskaner.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SatelliteDetailDTO(
    val id: Int,
    @SerialName("cost_per_launch")
    val costPerLaunch: Long,
    @SerialName("first_flight")
    val firstFlight: String,
    val height: Int,
    val mass: Long
)
