package com.eniskaner.satellites.ui.viewholder

import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.domain.model.SatelliteUI
import com.eniskaner.feature.satellites.R
import com.eniskaner.feature.satellites.databinding.ItemSatelliteBinding
import com.eniskaner.satellites.ui.adapter.SatelliteClickListener

class SatelliteListViewHolder(
    val binding: ItemSatelliteBinding,
    val listener: SatelliteClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bindSatelliteItem(item: SatelliteUI) = with(binding) {
        val background = if (item.isActive) {
            AppCompatResources.getDrawable(root.context, R.drawable.indicator_active)
        } else {
            AppCompatResources.getDrawable(root.context, R.drawable.indicator_inactive)
        }
        statusIndicator.background = background
        val alphaValue = if (item.isActive) 1f else 0.4f
        statusIndicator.alpha = alphaValue
        satelliteName.alpha = alphaValue
        satelliteStatus.alpha = alphaValue
        satelliteName.text = item.name
        satelliteStatus.text = if (item.isActive) "Active" else "Passive"
        lSatellite.setOnClickListener {
            listener.satelliteClicked(item.id, item.name)
        }
    }
}
