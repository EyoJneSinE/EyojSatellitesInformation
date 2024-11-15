package com.eniskaner.satellites.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eniskaner.common.util.Constants.DEFAULT_DELAY
import com.eniskaner.common.util.Constants.DEFAULT_ERROR_MESSAGE
import com.eniskaner.common.util.Resource
import com.eniskaner.domain.usecase.GetSatelliteListUseCase
import com.eniskaner.satellites.ui.state.SatelliteListUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SatellitesViewModel @Inject constructor(
    private val satelliteListUseCase: GetSatelliteListUseCase
) : ViewModel() {

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
                                error = resource.message ?: DEFAULT_ERROR_MESSAGE,
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
                        delay(DEFAULT_DELAY)
                    }
                }
            }
        }
    }
}
