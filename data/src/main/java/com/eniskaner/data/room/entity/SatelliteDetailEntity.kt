package com.eniskaner.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.eniskaner.common.util.Constants.DatabaseConstants.SATELLITE_DETAIL_TABLE_NAME

@Entity(SATELLITE_DETAIL_TABLE_NAME)
data class SatelliteDetailEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo("cost_per_launch")
    val costPerLaunch: Long,
    @ColumnInfo("first_flight")
    val firstFlight: String,
    val height: Int,
    val mass: Long
)
