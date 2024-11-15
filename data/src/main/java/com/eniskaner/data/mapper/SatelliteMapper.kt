package com.eniskaner.data.mapper

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

/**
 * PositionDTO to PositionUI Mapper
 */
@JvmSuppressWildcards
class PositionDTOToUIMapper : Mapper<@JvmSuppressWildcards PositionDTO, PositionUI> {
    override fun map(input: PositionDTO): PositionUI {
        return PositionUI(
            posX = input.posX,
            posY = input.posY
        )
    }
}

/**
 * SatelliteDetailDTO to SatelliteDetailUI Mapper
 */
@JvmSuppressWildcards
class SatelliteDetailDTOToUIMapper : Mapper<@JvmSuppressWildcards SatelliteDetailDTO, SatelliteDetailUI> {
    override fun map(input: SatelliteDetailDTO): SatelliteDetailUI {
        return SatelliteDetailUI(
            id = input.id,
            costPerLaunch = input.costPerLaunch,
            firstFlight = input.firstFlight,
            height = input.height,
            mass = input.mass
        )
    }
}

/**
 * SatelliteDTO to SatelliteUI Mapper
 */
@JvmSuppressWildcards
class SatelliteDTOToUIMapper : Mapper<@JvmSuppressWildcards SatelliteDTO, SatelliteUI> {
    override fun map(input: SatelliteDTO): SatelliteUI {
        return SatelliteUI(
            id = input.id,
            isActive = input.active,
            name = input.name
        )
    }
}

/**
 * SatellitePositionDTO to SatellitePositionUI Mapper
 */
@JvmSuppressWildcards
class SatellitePositionDTOToUIMapper(
    private val positionMapper: Mapper<@JvmSuppressWildcards PositionDTO, PositionUI>
) : Mapper<SatellitePositionDTO, SatellitePositionUI> {
    override fun map(input: SatellitePositionDTO): SatellitePositionUI {
        return SatellitePositionUI(
            id = input.id,
            positions = input.positions.map { positionMapper.map(it) }
        )
    }
}

/**
 * SatellitePositionDTO to SatellitePositionUI Mapper
 */
@JvmSuppressWildcards
class PositionListDTOToUIMapper(
    private val satellitePositionMapper: Mapper<@JvmSuppressWildcards SatellitePositionDTO, SatellitePositionUI>
) : Mapper<PositionListDTO, List<SatellitePositionUI>> {
    override fun map(input: PositionListDTO): List<SatellitePositionUI> {
        return input.list.map { satellitePositionMapper.map(it) }
    }
}

/**
 * SatelliteDetailDTO to SatelliteDetailEntity Mapper
 */
@JvmSuppressWildcards
class SatelliteDetailDTOToEntity : Mapper<@JvmSuppressWildcards SatelliteDetailDTO, SatelliteDetailEntity> {
    override fun map(input: SatelliteDetailDTO): SatelliteDetailEntity {
        return SatelliteDetailEntity(
            id = input.id,
            costPerLaunch = input.costPerLaunch,
            firstFlight = input.firstFlight,
            height = input.height,
            mass = input.mass
        )
    }
}

/**
 * SatelliteDetailEntity to SatelliteDetailUI Mapper
 */
@JvmSuppressWildcards
class SatelliteDetailEntityToUI : Mapper<@JvmSuppressWildcards SatelliteDetailEntity, SatelliteDetailUI> {
    override fun map(input: SatelliteDetailEntity): SatelliteDetailUI {
        return SatelliteDetailUI(
            id = input.id,
            costPerLaunch = input.costPerLaunch,
            firstFlight = input.firstFlight,
            height = input.height,
            mass = input.mass
        )
    }
}
