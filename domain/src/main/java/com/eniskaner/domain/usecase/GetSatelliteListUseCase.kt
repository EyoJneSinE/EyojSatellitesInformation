package com.eniskaner.domain.usecase

import com.eniskaner.data.mapper.SatelliteDTOToUIMapper
import com.eniskaner.domain.model.SatelliteUI
import com.eniskaner.domain.repository.SatelliteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSatelliteListUseCase @Inject constructor(
    private val satelliteRepository: SatelliteRepository,
    private val satelliteDTOToUIMapper: SatelliteDTOToUIMapper
) {
    suspend operator fun invoke() : Flow<List<SatelliteUI>> {
        return satelliteRepository.getSatelliteList().map { resource ->
            resource.data?.map { satelliteDTO ->
                satelliteDTOToUIMapper.map(satelliteDTO)
            } ?: emptyList()
        }
    }
}