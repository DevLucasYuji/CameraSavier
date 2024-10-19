package com.ujizin.leafy.core.local.mapper

import com.ujizin.leafy.core.local.model.AlbumEntity
import com.ujizin.leafy.core.repository.model.Album

/** Mapper between [Album] and [AlbumEntity] */
internal class AlbumMapper {

    fun toAlbumEntity(album: Album) = with(album) { AlbumEntity(id, title) }

    fun toAlbum(albumEntity: AlbumEntity) = with(albumEntity) { Album(id, title) }
}
