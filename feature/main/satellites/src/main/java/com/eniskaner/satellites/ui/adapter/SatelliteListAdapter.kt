package com.eniskaner.satellites.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.eniskaner.domain.model.SatelliteUI
import com.eniskaner.feature.satellites.databinding.ItemSatelliteBinding
import com.eniskaner.satellites.ui.viewholder.SatelliteListViewHolder

class SatelliteListAdapter(
    private val listener: SatelliteClickListener
): ListAdapter<SatelliteUI, SatelliteListViewHolder>(SatelliteDiffUtil()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SatelliteListViewHolder {
        val binding = ItemSatelliteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SatelliteListViewHolder(
            binding = binding,
            listener = listener
        )
    }

    override fun onBindViewHolder(holder: SatelliteListViewHolder, position: Int) {
        holder.bindSatelliteItem(getItem(position))
    }
}