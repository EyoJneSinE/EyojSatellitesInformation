package com.eniskaner.satellites.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.domain.model.SatelliteUI
import com.eniskaner.feature.satellites.R
import com.eniskaner.feature.satellites.databinding.ItemSatelliteBinding
import com.eniskaner.satellites.ui.adapter.SatelliteClickListener
import com.eniskaner.satellites.ui.util.setAlphaBasedOnStateForTextView
import com.eniskaner.satellites.ui.util.setAlphaBasedOnStateForView
import com.eniskaner.satellites.ui.util.setBackgroundBasedOnState
import com.eniskaner.satellites.ui.util.setTextBasedOnState

class SatelliteListViewHolder(
    val binding: ItemSatelliteBinding,
    val listener: SatelliteClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bindSatelliteItem(item: SatelliteUI) = with(binding) {
        statusIndicator.setBackgroundBasedOnState(
            isActive = item.isActive,
            activeDrawableRes = R.drawable.indicator_active,
            inactiveDrawableRes = R.drawable.indicator_inactive
        )

        statusIndicator.setAlphaBasedOnStateForView(item.isActive)
        satelliteName.setAlphaBasedOnStateForTextView(item.isActive)
        satelliteStatus.setAlphaBasedOnStateForTextView(item.isActive)
        satelliteName.text = item.name

        satelliteStatus.setTextBasedOnState(
            isActive = item.isActive,
            activeTextRes = R.string.active_status,
            passiveTextRes = R.string.passive_status
        )

        lSatellite.setOnClickListener {
            listener.satelliteClicked(item.id, item.name)
        }
    }
}
