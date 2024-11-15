package com.eniskaner.domain.usecase

import com.eniskaner.domain.repository.SatelliteRepository
import javax.inject.Inject

class GetSearchSatelliteListUseCase @Inject constructor(
    private val satelliteRepository: SatelliteRepository
) {
    suspend operator fun invoke(query: String) = satelliteRepository.search(query = query)
}