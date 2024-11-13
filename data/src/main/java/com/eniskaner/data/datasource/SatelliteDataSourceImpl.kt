package com.eniskaner.data.datasource

import android.content.res.AssetManager
import com.eniskaner.data.model.PositionListDTO
import com.eniskaner.data.model.SatelliteDTO
import com.eniskaner.data.model.SatelliteDetailDTO
import com.eniskaner.data.model.SatellitePositionDTO
import com.eniskaner.data.util.Constants.POSITIONS
import com.eniskaner.data.util.Constants.SATELLITE_DETAIL
import com.eniskaner.data.util.Constants.SATELLITE_LIST
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.InputStream
import javax.inject.Inject

@OptIn(ExperimentalSerializationApi::class)
class SatelliteDataSourceImpl @Inject constructor(
    private val assetManager: AssetManager,
    private val json: Json
) : SatelliteDataSource {

    override suspend fun getSatelliteList(): List<SatelliteDTO> =
        assetManager.open(SATELLITE_LIST).use(json::decodeFromStream)

    override suspend fun getSatelliteDetail(id: Int): SatelliteDetailDTO? =
        assetManager.open(SATELLITE_DETAIL)
            .use<InputStream, List<SatelliteDetailDTO>>(json::decodeFromStream).find {
                it.id == id
            }

    override suspend fun search(query: String): List<SatelliteDTO> =
        assetManager.open(SATELLITE_LIST)
            .use<InputStream, List<SatelliteDTO>>(json::decodeFromStream).filter {
                it.name.lowercase().contains(query.lowercase())
            }

    override suspend fun getSatellitePositions(id: Int): SatellitePositionDTO? =
        assetManager.open(POSITIONS)
            .use<InputStream, PositionListDTO>(json::decodeFromStream).list.find {
                it.id == id
            }
}
