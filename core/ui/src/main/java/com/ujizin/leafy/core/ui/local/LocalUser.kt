package com.ujizin.leafy.core.ui.local

import androidx.compose.runtime.compositionLocalOf
import com.ujizin.leafy.domain.model.Language.EN
import com.ujizin.leafy.domain.model.Language.PT
import com.ujizin.leafy.domain.model.Theme
import com.ujizin.leafy.domain.model.User
import com.ujizin.leafy.domain.model.User.Settings
import java.util.Locale

private val DefaultLanguage
    get() =
        when (Locale.getDefault().language) {
            "pt" -> PT
            else -> EN
        }

private val DefaultUser: User
    get() =
        User(
            nickname = "User",
            settings =
                Settings(theme = Theme.System, language = DefaultLanguage, dynamicColor = true),
        )

val LocalUser = compositionLocalOf { DefaultUser }
