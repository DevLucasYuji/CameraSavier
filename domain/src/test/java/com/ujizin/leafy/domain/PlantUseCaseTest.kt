package com.ujizin.leafy.domain

import com.ujizin.leafy.core.test.TestDispatcherRule
import com.ujizin.leafy.domain.model.Plant
import com.ujizin.leafy.domain.repository.PlantRepository
import com.ujizin.leafy.domain.result.Result
import com.ujizin.leafy.domain.usecase.plant.add.AddPlantUseCase
import com.ujizin.leafy.domain.usecase.plant.add.AddPlantUseCaseImpl
import com.ujizin.leafy.domain.usecase.plant.delete.DeletePlantUseCase
import com.ujizin.leafy.domain.usecase.plant.delete.DeletePlantUseCaseImpl
import com.ujizin.leafy.domain.usecase.plant.load.LoadAllPlantUseCase
import com.ujizin.leafy.domain.usecase.plant.load.LoadAllPlantUseCaseImpl
import com.ujizin.leafy.domain.usecase.plant.update.UpdatePlant
import com.ujizin.leafy.domain.usecase.plant.update.UpdatePlantImpl
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PlantUseCaseTest {

    @get:Rule val mainDispatcherRule = TestDispatcherRule()

    @MockK private lateinit var plantRepository: PlantRepository

    @MockK private lateinit var fakePlant: Plant

    private lateinit var addPlantUseCase: AddPlantUseCase

    private lateinit var loadPlantUseCase: LoadAllPlantUseCase

    private lateinit var updatePlantUseCase: UpdatePlant

    private lateinit var deletePlantUseCase: DeletePlantUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        addPlantUseCase = AddPlantUseCaseImpl(plantRepository)
        loadPlantUseCase = LoadAllPlantUseCaseImpl(plantRepository)
        updatePlantUseCase = UpdatePlantImpl(plantRepository)
        deletePlantUseCase = DeletePlantUseCaseImpl(plantRepository)
    }

    @Test
    fun `when add plant then expect call insert plant on repository`() = runTest {
        every { plantRepository.insertPlant(any()) } returns flowOf(0L)

        addPlantUseCase(fakePlant).collect {
            verify { plantRepository.insertPlant(fakePlant) }
            verify(exactly = 0) { plantRepository.insertPlants(listOf(fakePlant)) }
        }
    }

    @Test
    fun `when load plant then expect return plants on repository`() = runTest {
        val expectedPlants =
            listOf(
                Plant(id = 1, "", "", "", false),
                Plant(id = 2, "", "", "", false),
                Plant(id = 3, "", "", "", false),
            )

        every { plantRepository.getPlants() } returns flowOf(expectedPlants)

        loadPlantUseCase().collect { actualPlants ->
            if (actualPlants is Result.Success) {
                Assert.assertEquals(expectedPlants, actualPlants.data)
            }
        }
    }

    @Test
    fun `when update plant then expect call update plants on repository`() = runTest {
        every { plantRepository.updatePlant(fakePlant) } returns flowOf(Unit)

        updatePlantUseCase(fakePlant).collect { verify { plantRepository.updatePlant(fakePlant) } }
    }

    @Test
    fun `when delete plant then expect call delete plants on repository`() = runTest {
        every { plantRepository.deletePlant(fakePlant) } returns flowOf(Unit)

        deletePlantUseCase(fakePlant).collect { verify { plantRepository.deletePlant(fakePlant) } }
    }
}
