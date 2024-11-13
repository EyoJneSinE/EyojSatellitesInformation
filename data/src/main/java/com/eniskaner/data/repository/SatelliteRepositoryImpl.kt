package com.eniskaner.data.repository

import com.eniskaner.common.util.Resource
import com.eniskaner.data.datasource.SatelliteDataSource
import com.eniskaner.data.datasource.SatelliteLocalDataSource
import com.eniskaner.data.di.IoDispatcher
import com.eniskaner.data.mapper.SatelliteDetailDTOToEntity
import com.eniskaner.data.model.SatelliteDTO
import com.eniskaner.data.model.SatelliteDetailDTO
import com.eniskaner.data.model.SatellitePositionDTO
import com.eniskaner.data.room.entity.SatelliteDetailEntity
import com.eniskaner.data.util.Constants.DEFAULT_ERROR_MESSAGE
import com.eniskaner.domain.model.SatelliteDetailUI
import com.eniskaner.domain.model.SatellitePositionUI
import com.eniskaner.domain.model.SatelliteUI
import com.eniskaner.domain.repository.SatelliteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SatelliteRepositoryImpl @Inject constructor(
    private val satelliteDataSource: SatelliteDataSource,
    private val satelliteLocalDataSource: SatelliteLocalDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : SatelliteRepository {

    override suspend fun getSatelliteList(): Flow<Resource<List<SatelliteDTO>>> = flow {
        emit(Resource.Loading())
        val satellites = satelliteDataSource.getSatelliteList()
        emit(Resource.Success(data = satellites))
    }.catch {
        emit(Resource.Error(message = it.localizedMessage ?: DEFAULT_ERROR_MESSAGE))
    }

    override suspend fun getSatelliteDetail(id: Int): Flow<Resource<SatelliteDetailDTO?>> = flow {
        emit(Resource.Loading())
        val satelliteDetail = satelliteDataSource.getSatelliteDetail(id = id)
        emit(Resource.Success(data = satelliteDetail))
    }.catch {
        emit(Resource.Error(message = it.localizedMessage ?: DEFAULT_ERROR_MESSAGE))
    }

    override suspend fun search(query: String): Flow<Resource<List<SatelliteDTO>>> = flow {
        emit(Resource.Loading())
        val satelliteListSearch = satelliteDataSource.search(query = query)
        emit(Resource.Success(data = satelliteListSearch))
    }.catch {
        emit(Resource.Error(message = it.localizedMessage ?: DEFAULT_ERROR_MESSAGE))
    }

    override suspend fun getSatellitePosition(id: Int): Flow<Resource<SatellitePositionDTO?>> = flow {
        emit(Resource.Loading())
        val satellitePosition = satelliteDataSource.getSatellitePositions(id = id)
        emit(Resource.Success(data = satellitePosition))
    }.catch {
        emit(Resource.Error(message = it.localizedMessage ?: DEFAULT_ERROR_MESSAGE))
    }

    override suspend fun getSatelliteDetailFromDB(id: Int): Flow<Resource<SatelliteDetailEntity?>> = flow {
        emit(Resource.Loading())
        val satelliteDetail = satelliteLocalDataSource.getSatelliteDetail(id = id)
        emit(Resource.Success(data = satelliteDetail))
    }.catch {
        emit(Resource.Error(message = it.localizedMessage ?: DEFAULT_ERROR_MESSAGE))
    }
}
