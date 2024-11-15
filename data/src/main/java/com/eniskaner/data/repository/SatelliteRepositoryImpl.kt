package com.eniskaner.data.repository

import com.eniskaner.common.util.Constants.ErrorMessages.DEFAULT_ERROR_MESSAGE
import com.eniskaner.common.util.Resource
import com.eniskaner.data.datasource.SatelliteDataSource
import com.eniskaner.data.datasource.SatelliteLocalDataSource
import com.eniskaner.data.mapper.Mapper
import com.eniskaner.data.model.SatelliteDTO
import com.eniskaner.data.model.SatelliteDetailDTO
import com.eniskaner.data.model.SatellitePositionDTO
import com.eniskaner.data.room.entity.SatelliteDetailEntity
import com.eniskaner.domain.model.SatelliteDetailUI
import com.eniskaner.domain.model.SatellitePositionUI
import com.eniskaner.domain.model.SatelliteUI
import com.eniskaner.domain.repository.SatelliteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SatelliteRepositoryImpl @Inject constructor(
    private val satelliteDataSource: SatelliteDataSource,
    private val satelliteLocalDataSource: SatelliteLocalDataSource,
    @JvmSuppressWildcards
    private val satelliteDTOToUIMapper: Mapper<@JvmSuppressWildcards SatelliteDTO, SatelliteUI>,
    @JvmSuppressWildcards
    private val satelliteDetailDTOToUIMapper: Mapper<@JvmSuppressWildcards SatelliteDetailDTO, SatelliteDetailUI>,
    @JvmSuppressWildcards
    private val satelliteDetailEntityToUI: Mapper<@JvmSuppressWildcards SatelliteDetailEntity, SatelliteDetailUI>,
    @JvmSuppressWildcards
    private val satelliteDetailDTOToEntity: Mapper<@JvmSuppressWildcards SatelliteDetailDTO, SatelliteDetailEntity>,
    @JvmSuppressWildcards
    private val satellitePositionDTOToUIMapper: Mapper<@JvmSuppressWildcards SatellitePositionDTO, SatellitePositionUI>
) : SatelliteRepository {

    override suspend fun getSatelliteList(): Flow<Resource<List<SatelliteUI>>> = flow {
        emit(Resource.Loading())
        val satellites =
            satelliteDataSource.getSatelliteList().map { satelliteDTOToUIMapper.map(it) }
        emit(Resource.Success(data = satellites))
    }.catch {
        emit(Resource.Error(message = it.localizedMessage ?: DEFAULT_ERROR_MESSAGE))
    }

    override suspend fun getSatelliteDetail(id: Int): Flow<Resource<SatelliteDetailUI?>> = flow {
        emit(Resource.Loading())
        val satelliteDetailUI = satelliteLocalDataSource.getSatelliteDetail(
            id = id
        )?.let { satelliteDetailEntityToUI.map(it) } ?: satelliteDataSource.getSatelliteDetail(
            id = id
        )?.let { satelliteDetailDTOToUIMapper.map(it) }
        val localSatelliteDetail = satelliteDataSource.getSatelliteDetail(
            id = id
        )?.let { satelliteDetailDTOToEntity.map(it) }
        localSatelliteDetail?.let { satelliteLocalDataSource.saveSatelliteDetail(it) }
        emit(Resource.Success(data = satelliteDetailUI))
    }.catch {
        emit(Resource.Error(message = it.localizedMessage ?: DEFAULT_ERROR_MESSAGE))
    }

    override suspend fun search(query: String): Flow<Resource<List<SatelliteUI>>> = flow {
        emit(Resource.Loading())
        val satelliteListSearch =
            satelliteDataSource.search(query = query).map { satelliteDTOToUIMapper.map(it) }
        emit(Resource.Success(data = satelliteListSearch))
    }.catch {
        emit(Resource.Error(message = it.localizedMessage ?: DEFAULT_ERROR_MESSAGE))
    }

    override suspend fun getSatellitePosition(id: Int): Flow<Resource<SatellitePositionUI?>> =
        flow {
            emit(Resource.Loading())
            val satellitePosition = satelliteDataSource.getSatellitePositions(id = id)
                ?.let { satellitePositionDTOToUIMapper.map(it) }
            emit(Resource.Success(data = satellitePosition))
        }.catch {
            emit(Resource.Error(message = it.localizedMessage ?: DEFAULT_ERROR_MESSAGE))
        }
}
