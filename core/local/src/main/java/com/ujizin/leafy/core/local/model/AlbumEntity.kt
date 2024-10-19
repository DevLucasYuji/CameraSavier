package com.ujizin.leafy.core.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Album [Entity]
 *
 * @param id the album id
 * @param title the album title
 */
@Entity(tableName = "album")
data class AlbumEntity(
    @ColumnInfo(name = "album_id") @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "album_title") val title: String,
)
