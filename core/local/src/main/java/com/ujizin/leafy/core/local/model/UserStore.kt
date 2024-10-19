package com.ujizin.leafy.core.local.model

import kotlinx.serialization.Serializable

@Serializable
data class UserStore(
    val nickname: String,
    val theme: String? = null,
    val language: String,
    val dynamicColor: Boolean,
    val createdAt: String? = null,
) {

    companion object {
        private const val DEFAULT_NICKNAME = "User"

        fun default() =
            UserStore(
                nickname = DEFAULT_NICKNAME,
                theme = null,
                language = "EN",
                dynamicColor = true,
                createdAt = " ",
            )
    }
}

fun UserStore?.orDefault() = this ?: UserStore.default()
