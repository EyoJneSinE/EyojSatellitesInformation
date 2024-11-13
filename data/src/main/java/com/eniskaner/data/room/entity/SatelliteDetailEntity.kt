package com.eniskaner.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("satellite_detail")
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
