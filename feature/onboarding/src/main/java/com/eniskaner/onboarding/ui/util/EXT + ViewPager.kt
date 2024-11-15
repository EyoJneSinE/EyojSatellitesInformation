package com.eniskaner.onboarding.ui.util

import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.eniskaner.feature.onboarding.R

fun Fragment.updateViewPagerIndex(increment: Boolean) {
    val viewPager = activity?.findViewById<ViewPager2>(R.id.satellite_view_pager)
    viewPager?.let {
        val nextIndex = if (increment) it.currentItem + 1 else it.currentItem - 1
        val itemCount = it.adapter?.itemCount
        itemCount?.let {
            if (nextIndex in 0 until itemCount.coerceAtLeast(0)) {
                viewPager.currentItem = nextIndex
            }
        }
    }
}