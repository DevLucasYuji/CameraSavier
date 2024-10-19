package com.ujizin.leafy.alarm.ui

import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ujizin.leafy.alarm.receiver.AlarmReceiver
import com.ujizin.leafy.alarm.scheduler.AlarmScheduler
import com.ujizin.leafy.core.ui.extensions.copyAndDelete
import com.ujizin.leafy.core.ui.extensions.getNearestDay
import com.ujizin.leafy.domain.model.Alarm
import com.ujizin.leafy.domain.model.Ringtone
import com.ujizin.leafy.domain.model.WeekDay
import com.ujizin.leafy.domain.result.mapResult
import com.ujizin.leafy.domain.usecase.alarm.add.AddAlarmUseCase
import com.ujizin.leafy.domain.usecase.plant.add.AddPlantUseCase
import com.ujizin.leafy.domain.usecase.plant.load.LoadDraftPlantUseCase
import com.ujizin.leafy.domain.usecase.ringtone.load.LoadRingtonesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

@HiltViewModel
class AlarmViewModel
@Inject
constructor(
    private val loadDraftPlant: LoadDraftPlantUseCase,
    private val addPlant: AddPlantUseCase,
    private val addAlarm: AddAlarmUseCase,
    private val alarmScheduler: AlarmScheduler,
    private val getRingtones: LoadRingtonesUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<AlarmUiState>(AlarmUiState.Initial)
    val uiState: StateFlow<AlarmUiState> = _uiState

    fun setupRingtones() {
        getRingtones()
            .onEach { ringtones ->
                _uiState.update { AlarmUiState.Initialized(ringtones.distinctBy { it.title }) }
            }
            .launchIn(viewModelScope)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun addPlantWithAlarm(
        plantsDir: File,
        ringtone: Ringtone,
        hours: Int,
        minutes: Int,
        weekDays: List<WeekDay>,
        onPlantPublished: () -> Unit,
    ) {
        val ringtoneContent = ringtone.uriContent
        loadDraftPlant()
            .mapResult()
            .map {
                val draftFile = File(it.filePath)
                val plantFile = File(plantsDir, draftFile.name)
                it.copy(filePath = draftFile.copyAndDelete(plantFile).absolutePath)
            }
            .flatMapConcat { plant ->
                addPlant(plant.copy(id = 0)).flatMapConcat { id ->
                    addAlarm(
                            Alarm(
                                plantId = id,
                                ringtoneUriContent = ringtoneContent,
                                minutes = minutes,
                                hours = hours,
                                weekDays = weekDays.sorted(),
                                enabled = true,
                            )
                        )
                        .onEach { alarmId ->
                            alarmScheduler.scheduleAlarm(
                                dayOfWeek = weekDays.getNearestDay(hours, minutes).ordinal + 1,
                                hours = hours,
                                minutes = minutes,
                                ringtoneUri = ringtoneContent,
                                requestCode = alarmId.toInt(),
                                bundle = bundleOf(AlarmReceiver.ALARM_ID_EXTRA to alarmId),
                            )
                        }
                }
            }
            .onCompletion { onPlantPublished() }
            .catch {
                // Handle error
            }
            .launchIn(viewModelScope)
    }
}

sealed interface AlarmUiState {
    data object Initial : AlarmUiState

    data class Initialized(val ringtones: List<Ringtone>) : AlarmUiState
}
