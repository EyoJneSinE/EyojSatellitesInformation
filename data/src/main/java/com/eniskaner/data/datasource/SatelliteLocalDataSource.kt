package com.eniskaner.data.datasource

import com.eniskaner.data.room.entity.SatelliteDetailEntity

interface SatelliteLocalDataSource {

    suspend fun getSatelliteDetail(id: Int): SatelliteDetailEntity?

    suspend fun saveSatelliteDetail(satelliteDetailEntity: SatelliteDetailEntity)
}
