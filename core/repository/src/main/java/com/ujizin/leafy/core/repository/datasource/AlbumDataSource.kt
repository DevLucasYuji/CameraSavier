package com.ujizin.leafy.core.repository.datasource

import com.ujizin.leafy.core.repository.model.Album

/** Interface Album to data source implementation. */
interface AlbumDataSource {

    /**
     * Insert a new [Album].
     *
     * @param album [Album] to be added
     */
    suspend fun insertAlbum(album: Album)

    /**
     * Insert a new list of [Album].
     *
     * @param albums list of [Album] to be added
     */
    suspend fun insertAlbums(albums: List<Album>)

    /**
     * Get all [Album] from data source.
     *
     * @return all [Album] added
     */
    suspend fun getAlbums(): List<Album>

    /**
     * Find [Album] by id.
     *
     * @param albumId id's album
     * @return [Album] found or null
     */
    suspend fun findAlbumById(albumId: Long): Album?

    /**
     * Update [Album] passed on parameter
     *
     * @param album [Album] to be updated
     */
    suspend fun updateAlbum(album: Album)

    /**
     * Delete [Album] passed on parameter
     *
     * @param album [Album] to be deleted
     */
    suspend fun deleteAlbum(album: Album)
}
