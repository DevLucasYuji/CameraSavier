package com.ujizin.leafy.core.repository.model

/**
 * User model
 *
 * @param nickname nickname from user
 * @param theme theme used for user
 * @param language language user for user
 */
data class User(
    val nickname: String,
    val theme: String?,
    val language: String,
    val dynamicColor: Boolean,
)
