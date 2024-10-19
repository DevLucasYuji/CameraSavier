package com.ujizin.leafy.core.repository.mapper

import com.ujizin.leafy.core.repository.model.User as DataUser
import com.ujizin.leafy.domain.model.Language
import com.ujizin.leafy.domain.model.Theme
import com.ujizin.leafy.domain.model.User

/** User mapper between domain and data modules. */
internal class UserMapper {

    fun toDomain(user: DataUser): User =
        with(user) {
            User(
                nickname = nickname,
                settings =
                    User.Settings(
                        language = language.let(Language::valueOf),
                        theme = theme?.let(Theme::valueOf) ?: Theme.System,
                        dynamicColor = dynamicColor,
                    ),
            )
        }

    fun toData(user: User): DataUser =
        with(user) {
            DataUser(
                nickname = nickname,
                theme = settings.theme.toString(),
                language = settings.language.toString(),
                dynamicColor = settings.dynamicColor,
            )
        }
}
