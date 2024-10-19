package com.ujizin.leafy.domain.model

/**
 * User model
 *
 * @param nickname nickname from user
 * @param settings settings from user
 */
data class User(val nickname: String, val settings: Settings) {

    /**
     * Settings from user.
     *
     * @param theme user theme
     * @param language user language
     * @param dynamicColor check if user uses dynamic color
     */
    data class Settings(val theme: Theme, val language: Language, val dynamicColor: Boolean)
}

/** update user fields. */
fun User.update(
    nickname: String? = null,
    theme: Theme? = null,
    language: Language? = null,
    dynamicColor: Boolean? = null,
) =
    copy(
        nickname = nickname ?: this.nickname,
        settings =
            settings.copy(
                theme = theme ?: settings.theme,
                language = language ?: settings.language,
                dynamicColor = dynamicColor ?: settings.dynamicColor,
            ),
    )
