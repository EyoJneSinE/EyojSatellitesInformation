package com.eniskaner.data.di

import com.eniskaner.data.mapper.Mapper
import com.eniskaner.data.mapper.PositionDTOToUIMapper
import com.eniskaner.data.mapper.PositionListDTOToUIMapper
import com.eniskaner.data.mapper.SatelliteDTOToUIMapper
import com.eniskaner.data.mapper.SatelliteDetailDTOToEntity
import com.eniskaner.data.mapper.SatelliteDetailDTOToUIMapper
import com.eniskaner.data.mapper.SatelliteDetailEntityToUI
import com.eniskaner.data.mapper.SatellitePositionDTOToUIMapper
import com.eniskaner.data.model.PositionDTO
import com.eniskaner.data.model.PositionListDTO
import com.eniskaner.data.model.SatelliteDTO
import com.eniskaner.data.model.SatelliteDetailDTO
import com.eniskaner.data.model.SatellitePositionDTO
import com.eniskaner.data.room.entity.SatelliteDetailEntity
import com.eniskaner.domain.model.PositionUI
import com.eniskaner.domain.model.SatelliteDetailUI
import com.eniskaner.domain.model.SatellitePositionUI
import com.eniskaner.domain.model.SatelliteUI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    @Singleton
    @JvmSuppressWildcards
    fun providePositionDTOToUIMapper(): Mapper<@JvmSuppressWildcards PositionDTO, PositionUI> =
        PositionDTOToUIMapper()

    @Provides
    @Singleton
    @JvmSuppressWildcards
    fun provideSatelliteDetailDTOToUIMapper(): Mapper<@JvmSuppressWildcards SatelliteDetailDTO, SatelliteDetailUI> =
        SatelliteDetailDTOToUIMapper()

    @Provides
    @Singleton
    @JvmSuppressWildcards
    fun provideSatelliteDTOToUIMapper(): Mapper<@JvmSuppressWildcards SatelliteDTO, SatelliteUI> =
        SatelliteDTOToUIMapper()

    @Provides
    @Singleton
    @JvmSuppressWildcards
    fun provideSatellitePositionDTOToUIMapper(
        positionMapper: Mapper<@JvmSuppressWildcards PositionDTO, PositionUI>
    ): Mapper<SatellitePositionDTO, SatellitePositionUI> =
        SatellitePositionDTOToUIMapper(positionMapper)

    @Provides
    @Singleton
    @JvmSuppressWildcards
    fun providePositionListDTOToUIMapper(
        satellitePositionMapper: Mapper<@JvmSuppressWildcards SatellitePositionDTO, SatellitePositionUI>
    ): Mapper<PositionListDTO, List<SatellitePositionUI>> =
        PositionListDTOToUIMapper(satellitePositionMapper)

    @Provides
    @Singleton
    @JvmSuppressWildcards
    fun provideSatelliteDetailDTOToEntity(): Mapper<@JvmSuppressWildcards SatelliteDetailDTO, SatelliteDetailEntity> =
        SatelliteDetailDTOToEntity()

    @Provides
    @Singleton
    @JvmSuppressWildcards
    fun provideSatelliteDetailEntityToUI(): Mapper<@JvmSuppressWildcards SatelliteDetailEntity, SatelliteDetailUI> =
        SatelliteDetailEntityToUI()
}
