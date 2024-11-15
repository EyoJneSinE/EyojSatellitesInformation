package com.eniskaner.satellites.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eniskaner.common.util.Constants.Delays.DEFAULT_DELAY
import com.eniskaner.common.util.Constants.ErrorMessages.DEFAULT_ERROR_MESSAGE
import com.eniskaner.common.util.Resource
import com.eniskaner.domain.model.SatelliteUI
import com.eniskaner.domain.usecase.GetSearchSatelliteListUseCase
import com.eniskaner.satellites.ui.event.SatelliteEvent
import com.eniskaner.satellites.ui.state.SatelliteListUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SatellitesSearchViewModel @Inject constructor(
    private val searchSatelliteListUseCase: GetSearchSatelliteListUseCase
) : ViewModel() {

    private val _stateSearchUIState = MutableStateFlow(SatelliteListUIState())
    val stateSearchUIState = _stateSearchUIState.asStateFlow()

    init {
        searchSatellite(query = _stateSearchUIState.value.query)
    }

    private fun searchSatellite(query: String) {
        viewModelScope.launch {
            searchSatelliteListUseCase.invoke(query = query).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _stateSearchUIState.update {
                            it.copy(
                                searchingSatelliteList = resource.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                    }

                    is Resource.Error -> {
                        _stateSearchUIState.update {
                            it.copy(
                                error = resource.message ?: DEFAULT_ERROR_MESSAGE,
                                isLoading = false
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _stateSearchUIState.update {
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

    fun filterSatellites(query: String): List<SatelliteUI> {
        return if (query.isEmpty()) {
            _stateSearchUIState.value.searchingSatelliteList
        } else {
            _stateSearchUIState.value.searchingSatelliteList.filter {
                it.name.contains(query.lowercase().trim(), ignoreCase = true)
            }
        }
    }

    fun onEvent(eventSatellites: SatelliteEvent) {
        when (eventSatellites) {
            is SatelliteEvent.SearchSatellites -> {
                searchSatellite(query = eventSatellites.query)
            }
        }
    }
}
