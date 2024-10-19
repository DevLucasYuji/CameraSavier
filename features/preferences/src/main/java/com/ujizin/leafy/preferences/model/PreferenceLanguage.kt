package com.ujizin.leafy.preferences.model

import android.content.Context
import com.ujizin.leafy.domain.model.Language
import com.ujizin.leafy.preferences.extensions.displayResId

data class PreferenceLanguage(val language: Language, val displayName: String)

fun getLanguages(context: Context) =
    Language.entries.map { PreferenceLanguage(it, context.getString(it.displayResId)) }
