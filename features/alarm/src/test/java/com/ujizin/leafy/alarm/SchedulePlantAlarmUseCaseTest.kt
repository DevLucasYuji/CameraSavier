package com.ujizin.leafy.alarm

import com.ujizin.leafy.alarm.fakes.FakeAlarmScheduler
import com.ujizin.leafy.alarm.fakes.FakeLoadAlarmUseCase
import com.ujizin.leafy.alarm.fakes.FakeLoadPlant
import com.ujizin.leafy.alarm.usecase.SchedulePlantAlarmUseCase
import com.ujizin.leafy.core.test.TestDispatcherRule
import com.ujizin.leafy.core.ui.extensions.currentDay
import com.ujizin.leafy.core.ui.extensions.isDayAlreadyPassed
import com.ujizin.leafy.domain.model.WeekDay
import io.mockk.every
import io.mockk.mockkConstructor
import io.mockk.mockkStatic
import java.util.Calendar
import java.util.Calendar.DAY_OF_WEEK
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SchedulePlantAlarmUseCaseTest {

    @get:Rule val mainDispatcherRule = TestDispatcherRule()

    private val alarmScheduler = FakeAlarmScheduler()

    private val loadPlant = FakeLoadPlant(until = COUNTER)

    private val loadAlarm = FakeLoadAlarmUseCase(until = COUNTER)

    val schedulePlantAlarm =
        SchedulePlantAlarmUseCase(
            loadPlant = loadPlant,
            loadAlarm = loadAlarm,
            alarmScheduler = alarmScheduler,
        )

    @Test
    fun `test alarm is scheduled will ring in exact time whether enabled`() {
        var isFlowCollected = false
        mockkConstructor(Calendar::class)
        mockkStatic(WeekDay::isDayAlreadyPassed)

        every { anyConstructed<Calendar>().set(any(), any()) } returns Unit
        every { anyConstructed<Calendar>().timeInMillis = any() } returns Unit

        every { any<WeekDay>().isDayAlreadyPassed(any(), any()) } returns false
        loadAlarm.alarms.forEach { alarm ->
            (1..7).forEach { dayOfTheWeek ->
                every { anyConstructed<Calendar>().get(DAY_OF_WEEK) } returns dayOfTheWeek
                runTest {
                    schedulePlantAlarm(alarm.id)
                        .flowOn(mainDispatcherRule.testDispatcher)
                        .onStart { isFlowCollected = false }
                        .onCompletion {
                            assertEquals(alarm.weekDays.contains(currentDay), isFlowCollected)
                        }
                        .collect {
                            isFlowCollected = true

                            val alarmScheduled =
                                alarmScheduler.alarms.find { scheduled ->
                                    scheduled.hours == alarm.hours &&
                                        scheduled.minutes == alarm.minutes
                                }

                            assertNotNull(alarmScheduled)
                            assertEquals(alarm.plantId, it.id)
                        }
                }
            }
        }
    }

    companion object {
        private const val COUNTER = 100
    }
}
