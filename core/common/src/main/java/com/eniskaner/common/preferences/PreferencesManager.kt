package com.eniskaner.common.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.eniskaner.common.util.Constants.PreferenceKeys.SATELLITE_APP_PREFERENCES
import com.eniskaner.common.util.Constants.PreferenceKeys.SATELLITE_ONBOARDING_KEY_PREFS

class PreferencesManager(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SATELLITE_APP_PREFERENCES, Context.MODE_PRIVATE)

    var isSatelliteOnBoardingCompleted: Boolean
        get() = sharedPreferences.getBoolean(SATELLITE_ONBOARDING_KEY_PREFS, false)
        set(value) = sharedPreferences.edit {
            putBoolean(SATELLITE_ONBOARDING_KEY_PREFS, value)
        }
}
