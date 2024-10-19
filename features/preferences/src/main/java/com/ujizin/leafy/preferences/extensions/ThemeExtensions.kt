package com.ujizin.leafy.preferences.extensions

import androidx.annotation.StringRes
import com.ujizin.leafy.domain.model.Theme
import com.ujizin.leafy.features.preferences.R

val Theme.displayResId: Int
    @StringRes
    get() =
        when (this) {
            Theme.System -> R.string.system
            Theme.Dark -> R.string.dark
            Theme.Light -> R.string.light
        }
