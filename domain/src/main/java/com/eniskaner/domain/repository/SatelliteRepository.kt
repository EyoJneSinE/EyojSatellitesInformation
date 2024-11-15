package com.eniskaner.domain.repository

import com.eniskaner.common.util.Resource
import com.eniskaner.domain.model.SatelliteDetailUI
import com.eniskaner.domain.model.SatellitePositionUI
import com.eniskaner.domain.model.SatelliteUI
import kotlinx.coroutines.flow.Flow

interface SatelliteRepository {

    suspend fun getSatelliteList(): Flow<Resource<List<SatelliteUI>>>

    suspend fun getSatelliteDetail(id: Int): Flow<Resource<SatelliteDetailUI?>>

    suspend fun search(query: String): Flow<Resource<List<SatelliteUI>>>

    suspend fun getSatellitePosition(id: Int): Flow<Resource<SatellitePositionUI?>>

}
