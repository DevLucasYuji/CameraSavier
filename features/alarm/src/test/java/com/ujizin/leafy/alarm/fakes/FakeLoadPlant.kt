package com.ujizin.leafy.alarm.fakes

import com.ujizin.leafy.domain.model.Plant
import com.ujizin.leafy.domain.result.Result
import com.ujizin.leafy.domain.usecase.plant.load.LoadPlantUseCase
import kotlinx.coroutines.flow.flow

class FakeLoadPlant(
    private val until: Int = 10,
    private val plants: List<Plant> = (0..until).map { createPlant(id = it + 1L) },
) : LoadPlantUseCase {

    override fun invoke(id: Long) =
        flow<Result<Plant?>> {
            val result =
                plants.find { it.id == id }?.let { Result.Success(it) }
                    ?: Result.Error(Exception("Plant with id: $id not found"))

            emit(result)
        }

    companion object {
        private fun createPlant(
            id: Long = 1,
            title: String = "",
            description: String = "",
            filePath: String = "test",
            favorite: Boolean = false,
            albumId: Long? = null,
        ) = Plant(id, title, description, filePath, favorite, albumId)
    }
}
