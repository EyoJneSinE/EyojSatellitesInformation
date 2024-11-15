package com.eniskaner.satellites.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.eniskaner.domain.model.SatelliteUI

class SatelliteDiffUtil : DiffUtil.ItemCallback<SatelliteUI>() {
    override fun areItemsTheSame(
        oldItem: SatelliteUI,
        newItem: SatelliteUI
    ): Boolean = oldItem.id == newItem.id ||
            oldItem.name == newItem.name ||
            oldItem.isActive == newItem.isActive

    override fun areContentsTheSame(
        oldItem: SatelliteUI,
        newItem: SatelliteUI
    ): Boolean = oldItem == newItem

}
