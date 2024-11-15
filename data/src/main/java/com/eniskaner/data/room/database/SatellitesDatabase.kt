package com.eniskaner.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eniskaner.data.room.dao.SatelliteDao
import com.eniskaner.data.room.entity.SatelliteDetailEntity

@Database(
    entities = [SatelliteDetailEntity::class],
    version = 1,
    exportSchema = true
)
abstract class SatellitesDatabase : RoomDatabase() {

    abstract fun getSatelliteDao(): SatelliteDao
}
