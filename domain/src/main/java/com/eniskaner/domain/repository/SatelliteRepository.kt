package com.eniskaner.domain.repository

import com.eniskaner.common.util.Resource
import com.eniskaner.data.model.SatelliteDTO
import com.eniskaner.data.model.SatelliteDetailDTO
import com.eniskaner.data.model.SatellitePositionDTO
import com.eniskaner.data.room.entity.SatelliteDetailEntity
import kotlinx.coroutines.flow.Flow

interface SatelliteRepository {

    suspend fun getSatelliteList(): Flow<Resource<List<SatelliteDTO>>>

    suspend fun getSatelliteDetail(id: Int): Flow<Resource<SatelliteDetailDTO?>>

    suspend fun search(query: String): Flow<Resource<List<SatelliteDTO>>>

    suspend fun getSatellitePosition(id: Int): Flow<Resource<SatellitePositionDTO?>>

    suspend fun getSatelliteDetailFromDB(id: Int): Flow<Resource<SatelliteDetailEntity?>>
}