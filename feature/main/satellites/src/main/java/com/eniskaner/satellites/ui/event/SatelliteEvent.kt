package com.eniskaner.satellites.ui.event

sealed class SatelliteEvent {

    data class SearchSatellites(val query: String) : SatelliteEvent()
}