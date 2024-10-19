package com.ujizin.leafy.domain

import com.ujizin.leafy.core.test.TestDispatcherRule
import com.ujizin.leafy.domain.model.Alarm
import com.ujizin.leafy.domain.repository.AlarmRepository
import com.ujizin.leafy.domain.result.Result
import com.ujizin.leafy.domain.usecase.alarm.add.AddAlarmUseCase
import com.ujizin.leafy.domain.usecase.alarm.add.AddAlarmUseCaseImpl
import com.ujizin.leafy.domain.usecase.alarm.delete.DeleteAlarmUseCase
import com.ujizin.leafy.domain.usecase.alarm.delete.DeleteAlarmUseCaseImpl
import com.ujizin.leafy.domain.usecase.alarm.load.LoadAlarmUseCase
import com.ujizin.leafy.domain.usecase.alarm.load.LoadAlarmUseCaseImpl
import com.ujizin.leafy.domain.usecase.alarm.load.LoadAlarmsUseCase
import com.ujizin.leafy.domain.usecase.alarm.load.LoadAlarmsUseCaseImpl
import com.ujizin.leafy.domain.usecase.alarm.update.UpdateAlarmUseCase
import com.ujizin.leafy.domain.usecase.alarm.update.UpdateAlarmUseCaseImpl
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AlarmUseCaseTest {

    @get:Rule var mainCoroutineRule = TestDispatcherRule()

    @MockK private lateinit var alarmRepository: AlarmRepository

    private lateinit var loadAlarmUseCase: LoadAlarmUseCase
    private lateinit var loadAlarmsUseCase: LoadAlarmsUseCase
    private lateinit var updateAlarmsUseCase: UpdateAlarmUseCase
    private lateinit var deleteAlarmUseCase: DeleteAlarmUseCase
    private lateinit var addAlarmUseCase: AddAlarmUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        loadAlarmUseCase = LoadAlarmUseCaseImpl(alarmRepository)
        loadAlarmsUseCase = LoadAlarmsUseCaseImpl(alarmRepository)
        updateAlarmsUseCase = UpdateAlarmUseCaseImpl(alarmRepository)
        deleteAlarmUseCase = DeleteAlarmUseCaseImpl(alarmRepository)
        addAlarmUseCase = AddAlarmUseCaseImpl(alarmRepository)
    }

    @Test
    fun `given loadAlarmsUseCase then should return correct values`() = runTest {
        // Given
        every { alarmRepository.getAllAlarms() } returns flowOf(alarmList)

        // When
        val result = loadAlarmsUseCase().last()

        // Then
        Assert.assertEquals(Result.Success(alarmList), result)
    }

    @Test
    fun `given loadAlarmsUseCase, given plant id, then should return correct values`() = runTest {
        // Given
        every { alarmRepository.getAlarms(1) } returns flowOf(alarmList)

        // When
        val result = loadAlarmsUseCase(1).last()

        // Then
        Assert.assertEquals(Result.Success(alarmList), result)
    }

    @Test
    fun `given loadAlarmUseCase, given id, then should return correct value`() = runTest {
        // Given
        val expectedValue = alarmList[1]
        every { alarmRepository.getAlarmById(1) } returns flowOf(expectedValue)

        // When
        val result = loadAlarmUseCase(1).last()

        // Then
        Assert.assertEquals(Result.Success(expectedValue), result)
    }

    @Test
    fun `given addAlarmUseCase, given id, then should add with correct values`() = runTest {
        // Given
        val expectedValue = alarmList.first()
        every { alarmRepository.insertAlarm(expectedValue) } returns flowOf(expectedValue.id)

        // When
        val id = addAlarmUseCase(expectedValue).last()

        // Then
        verify { alarmRepository.insertAlarm(expectedValue) }
        Assert.assertEquals(expectedValue.id, id)
    }

    @Test
    fun `given updateAlarmUseCase, given id, then should update with correct values`() = runTest {
        // Given
        val expectedValue = alarmList.first()
        every { alarmRepository.updateAlarm(expectedValue) } returns flowOf(Unit)

        // When
        updateAlarmsUseCase(expectedValue).last()

        // Then
        verify { alarmRepository.updateAlarm(expectedValue) }
    }

    @Test
    fun `given deleteAlarmUseCase, given id, then should remove value`() = runTest {
        // Given
        val expectedValue = alarmList.first()
        every { alarmRepository.deleteAlarmByPlantId(expectedValue.plantId) } returns flowOf(Unit)

        // When
        deleteAlarmUseCase(expectedValue.plantId)

        // Then
        verify { alarmRepository.deleteAlarmByPlantId(expectedValue.plantId) }
    }

    companion object {
        private val alarmList =
            List(10) { Alarm(it.toLong(), it.toLong(), "ring", it, true, it, listOf()) }
    }
}
