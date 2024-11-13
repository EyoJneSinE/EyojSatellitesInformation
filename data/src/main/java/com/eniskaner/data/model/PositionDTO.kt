package com.eniskaner.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PositionDTO(
    val posX: Double,
    val posY: Double
)
