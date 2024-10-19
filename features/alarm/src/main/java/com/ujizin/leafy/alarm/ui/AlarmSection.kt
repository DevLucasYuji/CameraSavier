package com.ujizin.leafy.alarm.ui

import android.media.AudioAttributes
import android.media.RingtoneManager
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ujizin.leafy.alarm.extensions.alarmManager
import com.ujizin.leafy.alarm.extensions.hasAlarmPermission
import com.ujizin.leafy.alarm.extensions.mapToModalValue
import com.ujizin.leafy.alarm.extensions.orDefault
import com.ujizin.leafy.alarm.extensions.startAlarmPermission
import com.ujizin.leafy.alarm.model.RepeatMode
import com.ujizin.leafy.alarm.model.asCustom
import com.ujizin.leafy.alarm.ui.components.modal.MultiModalSelector
import com.ujizin.leafy.alarm.ui.components.timerbox.TimerBox
import com.ujizin.leafy.core.ui.components.Section
import com.ujizin.leafy.core.ui.components.animated.AnimatedButtonIcon
import com.ujizin.leafy.core.ui.components.animated.animation.Animation
import com.ujizin.leafy.core.ui.components.button.Button
import com.ujizin.leafy.core.ui.components.image.Icons
import com.ujizin.leafy.core.ui.components.modal.ModalBottomSheet
import com.ujizin.leafy.core.ui.components.selector.ModalSelector
import com.ujizin.leafy.core.ui.components.selector.ModalValue
import com.ujizin.leafy.core.ui.components.selector.Selector
import com.ujizin.leafy.core.ui.extensions.OnClick
import com.ujizin.leafy.core.ui.extensions.paddingScreen
import com.ujizin.leafy.core.ui.extensions.plantsDir
import com.ujizin.leafy.core.ui.state.observeAsState
import com.ujizin.leafy.domain.model.Ringtone
import com.ujizin.leafy.domain.model.WeekDay
import com.ujizin.leafy.features.alarm.R

@Composable
fun AlarmRoute(
    onBackPressed: OnClick,
    viewModel: AlarmViewModel = hiltViewModel(),
    onSaved: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val ringtones by remember {
        derivedStateOf {
            (uiState as? AlarmUiState.Initialized)?.ringtones.orEmpty().map {
                ModalValue(it.title, it)
            }
        }
    }
    var ringtone by
        remember(ringtones) { mutableStateOf(ringtones.firstOrNull().orDefault(context)) }
    var repeatMode: ModalValue<RepeatMode> by remember {
        mutableStateOf(ModalValue(context.getString(RepeatMode.Daily.display), RepeatMode.Daily))
    }
    val repeatValues =
        remember(repeatMode) {
            RepeatMode.getValues(repeatMode.value.asCustom()?.customWeekDays.orEmpty()).map {
                ModalValue(context.getString(it.display), it)
            }
        }

    var hours by remember { mutableIntStateOf(0) }
    var minutes by remember { mutableIntStateOf(0) }

    LaunchedEffect(viewModel) { viewModel.setupRingtones() }

    LaunchedEffect(context.alarmManager.hasAlarmPermission) { context.startAlarmPermission() }

    AlarmScreen(
        modifier = Modifier.fillMaxSize(),
        ringtoneValues = ringtones,
        repeatModeValues = repeatValues,
        onBackPressed = onBackPressed,
        repeat = repeatMode,
        ringtone = ringtone,
        onRingtoneChanged = { ringtone = it },
        onRepeatChanged = { repeatMode = it },
        onTimeChanged = { hour, minute ->
            hours = hour
            minutes = minute
        },
        onSaveClicked = {
            viewModel.addPlantWithAlarm(
                plantsDir = context.plantsDir,
                ringtone = ringtone.value,
                hours = hours,
                minutes = minutes,
                weekDays = repeatMode.value.weekDays,
                onPlantPublished = onSaved,
            )
        },
    )
}

@Composable
fun AlarmScreen(
    modifier: Modifier = Modifier,
    ringtoneValues: List<ModalValue<Ringtone>>,
    repeatModeValues: List<ModalValue<RepeatMode>>,
    ringtone: ModalValue<Ringtone>,
    repeat: ModalValue<RepeatMode>,
    onTimeChanged: (hours: Int, minutes: Int) -> Unit,
    onRepeatChanged: (ModalValue<RepeatMode>) -> Unit,
    onRingtoneChanged: (ModalValue<Ringtone>) -> Unit,
    onBackPressed: OnClick,
    onSaveClicked: OnClick,
) {
    val context = LocalContext.current
    val ringtonePlayer =
        remember(ringtone) {
            RingtoneManager.getRingtone(context, ringtone.value.uriContent.toUri())
        }
    var ringtoneShowModal by remember { mutableStateOf(false) }
    var repeatShowModal by remember { mutableStateOf(false) }

    RingtoneEffect(ringtonePlayer, ringtoneShowModal)

    AlarmContent(
        modifier = modifier,
        ringtone = ringtone.name,
        repeat = stringResource(repeat.value.display),
        ringtoneContent = {
            ModalSelector(
                title = stringResource(R.string.ringtone),
                currentValue = ringtone,
                values = ringtoneValues,
                onValueChanged = { ringtone ->
                    ringtonePlayer.stop()
                    onRingtoneChanged(ringtone)
                },
            )
        },
        repeatShowModal = repeatShowModal,
        ringtoneShowModal = ringtoneShowModal,
        repeatContent = {
            var showCustomSelector by remember { mutableStateOf(false) }

            ModalSelector(
                title = stringResource(R.string.repeat),
                currentValue = repeat,
                values = repeatModeValues,
                onValueChanged = { newRepeatMode ->
                    if (newRepeatMode.value is RepeatMode.Custom) {
                        showCustomSelector = true
                        return@ModalSelector
                    }

                    onRepeatChanged(newRepeatMode)
                    repeatShowModal = false
                },
            )

            ModalBottomSheet(
                showModal = showCustomSelector,
                skipPartiallyExpanded = true,
                onModalStateChanged = { showCustomSelector = it },
            ) {
                MultiModalSelector(
                    title = stringResource(id = R.string.custom),
                    currentValues =
                        repeat.value.asCustom()?.customWeekDays?.mapToModalValue().orEmpty(),
                    values = WeekDay.entries.toList().mapToModalValue(),
                    onValuesSelected = { weekDays ->
                        val repeatMode = RepeatMode.Custom(weekDays.map { it.value })
                        onRepeatChanged(
                            ModalValue(context.getString(repeatMode.display), repeatMode)
                        )
                        showCustomSelector = false
                        repeatShowModal = false
                    },
                )
            }
        },
        onRepeatModalStateChanged = { isVisible -> repeatShowModal = isVisible },
        onRingtoneModalStateChanged = { isVisible -> ringtoneShowModal = isVisible },
        onTimeChanged = onTimeChanged,
        onBackPressed = onBackPressed,
        onSaveClicked = onSaveClicked,
    )
}

@Composable
private fun AlarmContent(
    modifier: Modifier = Modifier,
    ringtone: String,
    repeat: String,
    onBackPressed: OnClick,
    repeatShowModal: Boolean,
    onRepeatModalStateChanged: (Boolean) -> Unit,
    ringtoneShowModal: Boolean,
    onRingtoneModalStateChanged: (Boolean) -> Unit,
    ringtoneContent: @Composable ColumnScope.() -> Unit,
    repeatContent: @Composable ColumnScope.() -> Unit,
    onTimeChanged: (hours: Int, minutes: Int) -> Unit,
    onSaveClicked: OnClick,
) {
    Section(
        modifier = modifier,
        title = stringResource(R.string.alarm_title),
        trailingIcon = { AnimatedButtonIcon(icon = Icons.Back, onClick = onBackPressed) },
        headerAnimation = Animation.None,
    ) {
        TimerBox(
            modifier = Modifier.fillMaxWidth().weight(1F).paddingScreen(),
            onTimeChange = onTimeChanged,
        )
        Selector(
            modifier = Modifier.fillMaxWidth().paddingScreen(vertical = 16.dp),
            title = stringResource(R.string.ringtone),
            onModalStateChanged = onRingtoneModalStateChanged,
            showModal = ringtoneShowModal,
            value = ringtone,
            content = ringtoneContent,
        )
        Selector(
            modifier = Modifier.fillMaxWidth().paddingScreen(vertical = 16.dp),
            title = stringResource(R.string.repeat),
            showModal = repeatShowModal,
            onModalStateChanged = onRepeatModalStateChanged,
            value = repeat,
            content = repeatContent,
        )
        Button(
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp).paddingScreen(),
            text = stringResource(R.string.save),
            onClick = onSaveClicked,
        )
    }
}

@Composable
fun RingtoneEffect(ringtonePlayer: android.media.Ringtone, isModalVisible: Boolean) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val lifecycleState by lifecycle.observeAsState()

    LaunchedEffect(lifecycleState) {
        if (
            lifecycleState == Lifecycle.Event.ON_STOP || lifecycleState == Lifecycle.Event.ON_PAUSE
        ) {
            ringtonePlayer.stop()
        }
    }

    LaunchedEffect(ringtonePlayer) {
        if (isModalVisible && !ringtonePlayer.isPlaying) {
            ringtonePlayer.audioAttributes =
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build()
            ringtonePlayer.play()
        }
    }

    LaunchedEffect(isModalVisible) { if (!isModalVisible) ringtonePlayer.stop() }
}
