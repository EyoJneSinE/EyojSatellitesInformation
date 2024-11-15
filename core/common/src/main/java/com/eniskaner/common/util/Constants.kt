package com.eniskaner.common.util

object Constants {
    object PreferenceKeys {
        const val SATELLITE_APP_PREFERENCES = "app_preferences"
        const val SATELLITE_ONBOARDING_KEY_PREFS = "onboarding_completed"
    }

    object Delays {
        const val DEFAULT_DELAY = 1500L
        const val SATELLITE_POSITION_DELAY = 3000L
    }

    object DatabaseConstants {
        const val SATELLITE_DB_NAME = "satellites.db"
        const val SATELLITE_DETAIL_TABLE_NAME = "satellite_detail"
    }

    object AssetConstants {
        const val SATELLITE_LIST = "satellite-list.json"
        const val SATELLITE_DETAIL = "satellite-detail.json"
        const val POSITIONS = "positions.json"
    }

    object ErrorMessages {
        const val DEFAULT_ERROR_MESSAGE = "An error occurred!"
    }
}
