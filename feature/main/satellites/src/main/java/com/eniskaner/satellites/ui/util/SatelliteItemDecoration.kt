package com.eniskaner.satellites.ui.util

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.feature.satellites.R

class SatelliteItemDecoration() : RecyclerView.ItemDecoration() {

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val paint = Paint().apply {
            color = ContextCompat.getColor(parent.context, R.color.eyoj_gray_400)
            strokeWidth = parent.context.resources.getDimension(R.dimen.divider_height)
        }
        val marginSmall = parent.context.resources.getDimension(R.dimen.margin_small)
        val left = parent.paddingLeft + marginSmall.toInt()
        val right = parent.width - parent.paddingRight - marginSmall.toInt()

        for (i in 0 until parent.childCount - 1) {
            val view = parent.getChildAt(i)
            val top = view.bottom + marginSmall / 2
            c.drawLine(left.toFloat(), top, right.toFloat(), top, paint)
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val marginSmall = parent.context.resources.getDimension(R.dimen.margin_small)
        val verticalMargin = marginSmall.toInt()
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = verticalMargin
        }
        outRect.bottom = verticalMargin
    }
}