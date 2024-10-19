package com.ujizin.leafy.domain.model

/**
 * Plant Model
 *
 * @param id the plant id
 * @param title the plant title
 * @param filePath the file path of the plant
 * @param description the plant description
 * @param favorite indicates if plant is favorite
 * @param albumId the album id linked to the plant
 */
data class Plant(
    val id: Long = 0,
    val title: String,
    val description: String,
    val filePath: String,
    val favorite: Boolean,
    val albumId: Long? = null,
)
