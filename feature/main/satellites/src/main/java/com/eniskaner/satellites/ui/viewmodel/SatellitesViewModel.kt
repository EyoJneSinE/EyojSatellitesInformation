package com.eniskaner.satellites.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eniskaner.common.util.Resource
import com.eniskaner.domain.usecase.GetSatelliteListUseCase
import com.eniskaner.domain.usecase.GetSearchSatelliteListUseCase
import com.eniskaner.satellites.ui.state.SatelliteListUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SatellitesViewModel @Inject constructor(
    private val satelliteListUseCase: GetSatelliteListUseCase,
    private val searchSatelliteListUseCase: GetSearchSatelliteListUseCase
): ViewModel() {

    private val _stateListUIState = MutableStateFlow(SatelliteListUIState())
    val stateListUIState = _stateListUIState.asStateFlow()

    init {
        getSatelliteList()
    }

    private fun getSatelliteList() {
        viewModelScope.launch {
            satelliteListUseCase.invoke().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _stateListUIState.update {
                            it.copy(
                                satelliteList = resource.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                    }
                    is Resource.Error -> {
                        _stateListUIState.update {
                            it.copy(
                                error = resource.message ?: "Error!",
                                isLoading = false
                            )
                        }
                    }
                    is Resource.Loading -> {
                        _stateListUIState.update {
                            it.copy(
                                isLoading = true
                            )
                        }
                    }
                }
            }
        }
    }
}
