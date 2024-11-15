package com.eniskaner.data.datasource

import com.eniskaner.data.room.database.SatellitesDatabase
import com.eniskaner.data.room.entity.SatelliteDetailEntity
import javax.inject.Inject

class SatelliteLocalDataSourceImpl @Inject constructor(
    private val satellitesDatabase: SatellitesDatabase
) : SatelliteLocalDataSource {

    override suspend fun getSatelliteDetail(id: Int): SatelliteDetailEntity? =
        satellitesDatabase.getSatelliteDao().getSatelliteDetail(id)

    override suspend fun saveSatelliteDetail(satelliteDetailEntity: SatelliteDetailEntity) =
        satellitesDatabase.getSatelliteDao().saveSatelliteDetail(satelliteDetailEntity)
}