package com.eniskaner.domain.usecase

import com.eniskaner.common.util.Resource
import com.eniskaner.domain.model.PositionUI
import com.eniskaner.domain.repository.SatelliteRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSatellitePositionUseCase @Inject constructor(
    private val satelliteRepository: SatelliteRepository
) {
    operator fun invoke(id: Int): Flow<Resource<PositionUI?>> = flow {
        val satellitePosition = satelliteRepository.getSatellitePosition(id)
        satellitePosition.collect { resource ->
            when (resource) {
                is Resource.Success -> {
                    var index = 0
                    val bound = resource.data?.positions?.size ?: 0
                    while (true) {
                        val position = resource.data?.positions?.getOrNull(index % bound)
                        emit(Resource.Success(position))
                        index++
                        delay(3000L)
                    }
                }
                is Resource.Error -> {
                    emit(Resource.Error(resource.message ?: ""))
                }
                is Resource.Loading -> {
                    emit(Resource.Loading())
                }
            }
        }
    }
}
