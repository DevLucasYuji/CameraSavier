package com.ujizin.leafy.core.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Plant [Entity]
 *
 * @param id the plant id
 * @param title the plant title
 * @param filePath file path of the plant
 * @param description the plant description
 * @param albumId the album id linked to the plant
 */
@Entity(
    tableName = "plant",
    foreignKeys =
        [
            ForeignKey(
                entity = AlbumEntity::class,
                parentColumns = ["album_id"],
                childColumns = ["album_id"],
                onDelete = ForeignKey.SET_DEFAULT,
            )
        ],
)
data class PlantEntity(
    @ColumnInfo(name = "plant_id") @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "plant_title") val title: String,
    @ColumnInfo(name = "plant_file_path") val filePath: String,
    @ColumnInfo(name = "plant_description") val description: String,
    @ColumnInfo(name = "plant_favorite") val favorite: Boolean,
    @ColumnInfo(name = "album_id", index = true) val albumId: Long? = null,
)
