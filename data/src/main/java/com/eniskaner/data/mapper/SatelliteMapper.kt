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
class PositionDTOToUIMapper : Mapper<PositionDTO, PositionUI> {
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
class SatelliteDetailDTOToUIMapper : Mapper<SatelliteDetailDTO, SatelliteDetailUI> {
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
class SatelliteDTOToUIMapper : Mapper<SatelliteDTO, SatelliteUI> {
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
class SatellitePositionDTOToUIMapper(
    private val positionMapper: Mapper<PositionDTO, PositionUI>
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
class PositionListDTOToUIMapper(
    private val satellitePositionMapper: Mapper<SatellitePositionDTO, SatellitePositionUI>
) : Mapper<PositionListDTO, List<SatellitePositionUI>> {
    override fun map(input: PositionListDTO): List<SatellitePositionUI> {
        return input.list.map { satellitePositionMapper.map(it) }
    }
}

/**
 * SatelliteDetailDTO to SatelliteDetailEntity Mapper
 */
class SatelliteDetailDTOToEntity(
    private val satelliteDetailEntityMapper: Mapper<SatelliteDetailDTO, SatelliteDetailEntity>
) : Mapper<SatelliteDetailDTO, SatelliteDetailEntity> {
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
class SatelliteDetailEntityToUI(
    private val satelliteDetailEntityMapper: Mapper<SatelliteDetailEntity, SatelliteDetailUI>
) : Mapper<SatelliteDetailEntity, SatelliteDetailUI> {
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
