package com.eniskaner.domain.usecase

import com.eniskaner.data.mapper.Mapper
import com.eniskaner.data.mapper.SatelliteDetailDTOToUIMapper
import com.eniskaner.data.mapper.SatellitePositionDTOToUIMapper
import com.eniskaner.data.model.PositionDTO
import com.eniskaner.data.model.SatellitePositionDTO
import com.eniskaner.domain.model.PositionUI
import com.eniskaner.domain.model.SatelliteDetailUI
import com.eniskaner.domain.model.SatellitePositionUI
import com.eniskaner.domain.repository.SatelliteRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSatellitePositionUseCase @Inject constructor(
    private val satelliteRepository: SatelliteRepository,
    private val satellitePositionDTOToUIMapper: Mapper<PositionDTO, PositionUI>
) {
    operator fun invoke(id: Int) : Flow<PositionUI> = flow {
        satelliteRepository.getSatellitePosition(id = id).collectLatest { resource ->
            var index = 0
            val bound = resource.data?.positions?.size ?: 0
            while (true) {
                val position = resource.data?.positions?.getOrNull(index % bound)
                position?.let { emit(satellitePositionDTOToUIMapper.map(it)) }
                index++
                delay(3000)
            }
        }
    }
}
