package com.ujizin.leafy.preferences.extensions

import androidx.annotation.StringRes
import com.ujizin.leafy.domain.model.Language
import com.ujizin.leafy.features.preferences.R

val Language.displayResId: Int
    @StringRes
    get() =
        when (this) {
            Language.PT -> R.string.portuguese
            Language.EN -> R.string.english
        }
