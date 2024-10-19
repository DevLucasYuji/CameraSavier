package com.ujizin.leafy.core.repository.model

/**
 * Contract Plant Model to PlantDataSource.
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
    val filePath: String,
    val description: String,
    val favorite: Boolean,
    val albumId: Long? = null,
)
