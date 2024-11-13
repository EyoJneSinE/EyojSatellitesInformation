package com.eniskaner.data.datasource

import com.eniskaner.data.model.SatelliteDTO
import com.eniskaner.data.model.SatelliteDetailDTO
import com.eniskaner.data.model.SatellitePositionDTO

interface SatelliteDataSource {

    suspend fun getSatelliteList(): List<SatelliteDTO>

    suspend fun getSatelliteDetail(id: Int): SatelliteDetailDTO?

    suspend fun search(query: String): List<SatelliteDTO>

    suspend fun getSatellitePositions(id: Int): SatellitePositionDTO?
}
