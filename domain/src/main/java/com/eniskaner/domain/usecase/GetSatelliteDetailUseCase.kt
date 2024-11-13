package com.eniskaner.domain.usecase

import com.eniskaner.data.mapper.SatelliteDTOToUIMapper
import com.eniskaner.data.mapper.SatelliteDetailDTOToUIMapper
import com.eniskaner.domain.model.SatelliteDetailUI
import com.eniskaner.domain.model.SatelliteUI
import com.eniskaner.domain.repository.SatelliteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSatelliteDetailUseCase @Inject constructor(
    private val satelliteRepository: SatelliteRepository,
    private val satelliteDetailDTOToUIMapper: SatelliteDetailDTOToUIMapper
) {
    suspend operator fun invoke(id: Int) : Flow<SatelliteDetailUI?> {
        return satelliteRepository.getSatelliteDetail(id = id).map { resource ->
            resource.data?.let { satelliteDetailDTOToUIMapper.map(it) }
        }
    }
}