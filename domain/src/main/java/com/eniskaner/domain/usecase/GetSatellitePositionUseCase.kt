package com.eniskaner.domain.usecase

import com.eniskaner.domain.repository.SatelliteRepository
import javax.inject.Inject

class GetSatellitePositionUseCase @Inject constructor(
    private val satelliteRepository: SatelliteRepository
) {
    suspend operator fun invoke(id: Int) = satelliteRepository.getSatellitePosition(id = id)
}
