package com.eniskaner.domain.repository

import com.eniskaner.domain.model.SatelliteDetailUI
import com.eniskaner.domain.model.SatellitePositionUI
import com.eniskaner.domain.model.SatelliteUI

interface SatelliteRepository {

    suspend fun getSatelliteList(): List<SatelliteUI>

    suspend fun getSatelliteDetail(id: Int): SatelliteDetailUI?

    suspend fun search(query: String): List<SatelliteUI>

    suspend fun getSatellitePosition(id: Int): SatellitePositionUI?
}