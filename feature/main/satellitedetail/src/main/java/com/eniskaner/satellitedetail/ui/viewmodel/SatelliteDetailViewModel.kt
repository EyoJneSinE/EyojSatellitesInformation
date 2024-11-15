package com.eniskaner.satellitedetail.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eniskaner.common.util.Constants.Delays.DEFAULT_DELAY
import com.eniskaner.common.util.Constants.ErrorMessages.DEFAULT_ERROR_MESSAGE
import com.eniskaner.common.util.Resource
import com.eniskaner.domain.usecase.GetSatelliteDetailUseCase
import com.eniskaner.domain.usecase.GetSatellitePositionUseCase
import com.eniskaner.satellitedetail.ui.model.SatelliteDetailUIModel
import com.eniskaner.satellitedetail.ui.state.SatelliteDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SatelliteDetailViewModel @Inject constructor(
    private val getSatelliteDetailUseCase: GetSatelliteDetailUseCase,
    private val getSatellitePositionUseCase: GetSatellitePositionUseCase
) : ViewModel() {

    private val _satelliteDetail = MutableStateFlow(SatelliteDetailState())
    val satelliteDetail = _satelliteDetail.asStateFlow()

    fun getSatelliteDetail(satelliteId: Int, satelliteName: String) {
        viewModelScope.launch {
            val satelliteDetailResult = getSatelliteDetailUseCase(satelliteId)
            val satellitePositionResult = getSatellitePositionUseCase(satelliteId)
            satelliteDetailResult.combine(satellitePositionResult) { detailResource, positionResource ->
                when {
                    detailResource is Resource.Success && positionResource is Resource.Success -> {
                        val satelliteDetail = detailResource.data
                        val satellitePosition = positionResource.data
                        if (satelliteDetail != null && satellitePosition != null) {
                            _satelliteDetail.update {
                                it.copy(
                                    satelliteDetail = SatelliteDetailUIModel(
                                        id = satelliteDetail.id,
                                        costPerLaunch = satelliteDetail.costPerLaunch,
                                        firstFlight = satelliteDetail.firstFlight,
                                        height = satelliteDetail.height,
                                        mass = satelliteDetail.mass,
                                        posX = satellitePosition.posX,
                                        posY = satellitePosition.posY,
                                        name = satelliteName
                                    ),
                                    isLoading = false
                                )
                            }
                        }
                    }

                    detailResource is Resource.Error -> {
                        _satelliteDetail.update {
                            it.copy(
                                error = detailResource.message ?: DEFAULT_ERROR_MESSAGE,
                                isLoading = false
                            )
                        }
                    }

                    positionResource is Resource.Error -> {
                        _satelliteDetail.update {
                            it.copy(
                                error = detailResource.message ?: DEFAULT_ERROR_MESSAGE,
                                isLoading = false
                            )
                        }
                    }

                    detailResource is Resource.Loading || positionResource is Resource.Loading -> {
                        _satelliteDetail.update {
                            it.copy(
                                isLoading = true
                            )
                        }
                        delay(DEFAULT_DELAY)
                    }
                }
            }.collect {}
        }
    }
}