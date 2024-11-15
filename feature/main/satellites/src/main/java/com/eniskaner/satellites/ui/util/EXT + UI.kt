package com.eniskaner.satellites.ui.util

import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.content.res.AppCompatResources

fun View.setAlphaBasedOnStateForView(isActive: Boolean) {
    this.alpha = if (isActive) 1f else 0.4f
}

fun TextView.setAlphaBasedOnStateForTextView(isActive: Boolean) {
    this.alpha = if (isActive) 1f else 0.4f
}

fun View.setBackgroundBasedOnState(
    isActive: Boolean,
    activeDrawableRes: Int,
    inactiveDrawableRes: Int
) {
    val background = if (isActive) {
        AppCompatResources.getDrawable(context, activeDrawableRes)
    } else {
        AppCompatResources.getDrawable(context, inactiveDrawableRes)
    }
    this.background = background
}

fun TextView.setTextBasedOnState(
    isActive: Boolean,
    @StringRes activeTextRes: Int,
    @StringRes passiveTextRes: Int
) {
    this.text = context.getString(if (isActive) activeTextRes else passiveTextRes)
}
