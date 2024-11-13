package com.eniskaner.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eniskaner.data.room.entity.SatelliteDetailEntity

@Dao
interface SatelliteDao {

    @Query("select * from satellite_detail where id = :id")
    suspend fun getSatelliteDetail(id: Int): SatelliteDetailEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSatelliteDetail(satelliteDetailEntity: SatelliteDetailEntity)

    @Delete
    suspend fun deleteSatelliteDetail(satelliteDetailEntity: SatelliteDetailEntity)
}
