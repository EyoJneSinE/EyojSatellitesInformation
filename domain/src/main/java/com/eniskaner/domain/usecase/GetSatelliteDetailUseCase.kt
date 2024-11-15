package com.eniskaner.domain.usecase

import com.eniskaner.domain.repository.SatelliteRepository
import javax.inject.Inject

class GetSatelliteDetailUseCase @Inject constructor(
    private val satelliteRepository: SatelliteRepository
) {
    suspend operator fun invoke(id: Int) = satelliteRepository.getSatelliteDetail(id = id)
}
