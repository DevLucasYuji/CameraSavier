package com.ujizin.leafy.local.datasource

import com.ujizin.leafy.core.local.dao.PlantDao
import com.ujizin.leafy.core.local.model.AlbumEntity
import com.ujizin.leafy.core.local.model.PlantEntity
import com.ujizin.leafy.local.BaseDatabaseTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class PlantLocalDataSourceTest : BaseDatabaseTest() {

    private lateinit var plantDao: PlantDao

    private val fakePlant =
        PlantEntity(
            id = 1,
            title = "fake-plant",
            filePath = ":data//fake/path",
            favorite = false,
            description = "this is a fake plant",
            albumId = null,
        )

    override fun setUp() {
        super.setUp()
        plantDao = db.plantDao()
    }

    @Test
    fun writePlantAndReadInList() = runTest {
        val fakePlant2 = fakePlant.copy(id = 2, title = "fake-plant 2")
        val fakePlant3 = fakePlant.copy(id = 3, title = "fake-plant 3")

        val expectedPlants = arrayOf(fakePlant, fakePlant2, fakePlant3)
        plantDao.insertAll(*expectedPlants)

        val actualPlants = plantDao.getAll()
        assertEquals(expectedPlants.toList(), actualPlants)
    }

    @Test
    fun writePlantAndFindByAlbumId() = runTest {
        val fakeAlbum = AlbumEntity(1, "title album")
        val fakeAlbum2 = AlbumEntity(2, "title album")

        db.albumDao().insert(fakeAlbum, fakeAlbum2)

        val fakePlant2 = fakePlant.copy(id = 2, title = "fake-plant 2", albumId = 1)
        val fakePlant3 = fakePlant.copy(id = 3, title = "fake-plant 3", albumId = 2)
        val fakePlant4 = fakePlant.copy(id = 4, title = "fake-plant 4", albumId = 2)

        plantDao.insertAll(fakePlant, fakePlant2, fakePlant3, fakePlant4)

        val expectedPlants = arrayOf(fakePlant3, fakePlant4)

        val actualPlants = plantDao.findByAlbumId(2)

        assertEquals(expectedPlants.toList(), actualPlants)
    }

    @Test
    fun writePlantAndUpdate() = runTest {
        val expectedPlant = fakePlant.copy(title = "updated title")

        plantDao.insert(fakePlant)

        plantDao.update(expectedPlant)

        assertEquals(expectedPlant, plantDao.getAll().first())
    }

    @Test
    fun writePlantAndDelete() = runTest {
        plantDao.insert(fakePlant)

        assertEquals(1, plantDao.getAll().size)

        plantDao.delete(fakePlant)

        assertEquals(0, plantDao.getAll().size)
    }
}
