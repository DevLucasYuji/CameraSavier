package com.ujizin.leafy.features.plant.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.ujizin.leafy.core.navigation.Destination
import com.ujizin.leafy.domain.model.Alarm
import com.ujizin.leafy.domain.model.Plant
import com.ujizin.leafy.domain.result.mapResult
import com.ujizin.leafy.domain.usecase.alarm.delete.DeleteAlarmUseCase
import com.ujizin.leafy.domain.usecase.alarm.load.LoadAlarmsUseCase
import com.ujizin.leafy.domain.usecase.alarm.update.UpdateAlarmUseCase
import com.ujizin.leafy.domain.usecase.plant.delete.DeletePlantUseCase
import com.ujizin.leafy.domain.usecase.plant.load.LoadPlantUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class DetailPlantViewModel
@Inject
constructor(
    savedStateHandle: SavedStateHandle,
    loadPlant: LoadPlantUseCase,
    loadAlarms: LoadAlarmsUseCase,
    private val deletePlantUseCase: DeletePlantUseCase,
    private val deleteAlarmUseCase: DeleteAlarmUseCase,
    private val updateAlarmUseCase: UpdateAlarmUseCase,
) : ViewModel() {

    private val arguments = savedStateHandle.toRoute<Destination.PlantDetails>()

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState =
        loadPlant(arguments.plantId)
            .mapResult()
            .flatMapConcat { plant ->
                loadAlarms(plant.id).mapResult().map { alarms ->
                    DetailPlantUiState.Loaded(plant, alarms)
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = DetailPlantUiState.Initial,
            )

    fun updateAlarm(alarm: Alarm) {
        updateAlarmUseCase(alarm).launchIn(viewModelScope)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun deletePlant(plant: Plant, onCompletion: () -> Unit) {
        deleteAlarmUseCase(arguments.plantId)
            .flatMapConcat { deletePlantUseCase(plant) }
            .onCompletion { onCompletion() }
            .launchIn(viewModelScope)
    }
}

sealed interface DetailPlantUiState {

    data object Initial : DetailPlantUiState

    data class Loaded(val plant: Plant, val alarms: List<Alarm>) : DetailPlantUiState

    data object NotFound : DetailPlantUiState
}
