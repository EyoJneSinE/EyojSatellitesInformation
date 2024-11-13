package com.eniskaner.common.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.eniskaner.common.util.Constants

class PreferencesManager(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(Constants.SATELLITE_APP_PREFERENCES, Context.MODE_PRIVATE)

    var isSatelliteOnBoardingCompleted: Boolean
        get() = sharedPreferences.getBoolean(Constants.SATELLITE_ONBOARDING_KEY_PREFS, false)
        set(value) = sharedPreferences.edit {
            putBoolean(Constants.SATELLITE_ONBOARDING_KEY_PREFS, value)
        }
}
