package com.eniskaner.satellites.ui.util

import android.text.Editable
import android.text.TextWatcher

class SatelliteSearchTextWatcher(
    private val onSearch: (String) -> Unit
) : TextWatcher {

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        val searchingSatelliteText = s.toString()
        onSearch(searchingSatelliteText)
    }

    override fun afterTextChanged(s: Editable?) {}
}
