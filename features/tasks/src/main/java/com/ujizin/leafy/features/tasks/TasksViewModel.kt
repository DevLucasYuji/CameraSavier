package com.ujizin.leafy.features.tasks

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ujizin.leafy.core.ui.extensions.reorderByCurrentDay
import com.ujizin.leafy.domain.model.WeekDay
import com.ujizin.leafy.domain.result.mapResult
import com.ujizin.leafy.domain.usecase.alarm.load.LoadAlarmsUseCase
import com.ujizin.leafy.domain.usecase.plant.load.LoadPlantUseCase
import com.ujizin.leafy.features.tasks.model.Task
import com.ujizin.leafy.features.tasks.model.TaskWeek
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

@HiltViewModel
class TasksViewModel
@Inject
constructor(private val loadAllAlarms: LoadAlarmsUseCase, private val loadPlant: LoadPlantUseCase) :
    ViewModel() {

    private val _uiState = MutableStateFlow<TasksUiState>(TasksUiState.Initial)
    val uiState = _uiState.asStateFlow()

    fun loadAlarms() {
        loadAllAlarms()
            .mapResult()
            .onEach { alarms ->
                val tasks =
                    WeekDay.entries.reorderByCurrentDay().mapNotNull { weekDay ->
                        val alarmHasWeekDay =
                            alarms.any { it.weekDays.contains(weekDay) && it.enabled }
                        when {
                            alarmHasWeekDay ->
                                TaskWeek(
                                    weekDay = weekDay,
                                    tasks =
                                        alarms
                                            .filter { it.enabled && it.weekDays.contains(weekDay) }
                                            .map { alarm ->
                                                Task(
                                                    plant =
                                                        loadPlant(alarm.plantId)
                                                            .mapResult()
                                                            .first(),
                                                    alarm = alarm,
                                                )
                                            },
                                )

                            else -> null
                        }
                    }

                _uiState.update {
                    if (tasks.isEmpty()) TasksUiState.Empty else TasksUiState.Success(tasks)
                }
            }
            .launchIn(viewModelScope)
    }
}

sealed interface TasksUiState {
    @Immutable data object Initial : TasksUiState

    @Immutable data object Empty : TasksUiState

    @Immutable data class Success(val tasks: List<TaskWeek>) : TasksUiState
}
