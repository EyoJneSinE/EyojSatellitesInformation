package com.eniskaner.domain.usecase

import com.eniskaner.domain.repository.SatelliteRepository
import javax.inject.Inject

class GetSatelliteListUseCase @Inject constructor(
    private val satelliteRepository: SatelliteRepository
) {
    suspend operator fun invoke() = satelliteRepository.getSatelliteList()
}