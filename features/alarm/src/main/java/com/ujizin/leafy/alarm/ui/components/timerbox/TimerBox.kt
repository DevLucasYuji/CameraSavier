package com.ujizin.leafy.alarm.ui.components.timerbox

import androidx.annotation.IntRange
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ujizin.leafy.alarm.ui.components.AutoSizeText
import com.ujizin.leafy.core.themes.LeafyTheme
import com.ujizin.leafy.core.ui.extensions.toDecimalFormat
import kotlin.math.floor

/**
 * Timer box with hour (00-23) and minute (00-59)
 *
 * @param hour initial hour from timer, valid values (00-23)
 * @param minute initial
 */
@Composable
fun TimerBox(
    modifier: Modifier = Modifier,
    @IntRange(0, 23) hour: Int? = null,
    @IntRange(0, 59) minute: Int? = null,
    onTimeChange: (hour: Int, minute: Int) -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(64.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        var internalHour by remember(Unit) { mutableStateOf(hour.toDecimalFormat()) }
        var internalMinute by remember(Unit) { mutableStateOf(minute.toDecimalFormat()) }
        TimerUnitBox(
            modifier = Modifier.weight(1F),
            timeUnit = TimeUnit.Hour,
            value = hour,
            horizontalAlignment = Alignment.End,
            onTimeChange = { internalHour = it },
        )
        TimerUnitBox(
            modifier = Modifier.weight(1F),
            timeUnit = TimeUnit.Minutes,
            value = minute,
            horizontalAlignment = Alignment.Start,
            onTimeChange = { internalMinute = it },
        )

        LaunchedEffect(internalHour, internalMinute) {
            onTimeChange(internalHour.toInt(), internalMinute.toInt())
        }
    }
}

/**
 * Timer Unit Box Composable
 *
 * @param quantity how many numbers it will appear
 * @param timeUnit time unit to Box (hour or minute)
 * @param value the initial value from time unit, if value is not valid then start with 00
 * @param horizontalAlignment horizontal alignment from box
 * @param onTimeChange callback for time unit
 */
@Composable
private fun TimerUnitBox(
    modifier: Modifier = Modifier,
    timeUnit: TimeUnit,
    quantity: Int = 5,
    value: Int? = null,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    onTimeChange: (String) -> Unit,
) {
    val state = rememberLazyListState()
    var hapticInitialized by remember { mutableStateOf(false) }
    val quantityMiddle = remember(quantity) { floor(quantity.toDouble() / 2).toInt() }

    LazyColumn(modifier = modifier, state = state, horizontalAlignment = horizontalAlignment) {
        items(Int.MAX_VALUE) { listIndex ->
            val index = listIndex % timeUnit.numbers.size
            val number = timeUnit.numbers[index]
            val middle by remember {
                derivedStateOf { state.firstVisibleItemIndex + quantityMiddle }
            }
            val endIndex by remember {
                derivedStateOf { state.firstVisibleItemIndex + (quantity - 1) }
            }

            val alpha by
                remember(listIndex) {
                    derivedStateOf {
                        when (listIndex) {
                            state.firstVisibleItemIndex,
                            endIndex -> 0.25F
                            middle -> 1F
                            in state.firstVisibleItemIndex until middle,
                            in middle until endIndex -> 0.5F

                            else -> 0F
                        }
                    }
                }

            val scaleMultiplier = remember(Unit) { 1.4F }
            val animatedScale by animateFloatAsState(targetValue = alpha * scaleMultiplier)

            AutoSizeText(
                modifier = Modifier.alpha(alpha).scale(animatedScale.coerceAtLeast(0.5F)),
                text = number,
                textStyle =
                    TextStyle(
                        fontSize = 48.sp,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold,
                    ),
            )

            val haptic = LocalHapticFeedback.current
            LaunchedEffect(remember { derivedStateOf { state.firstVisibleItemIndex } }) {
                if (hapticInitialized) {
                    haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        val half = Int.MAX_VALUE / 2 // Set Infinite loop
        val index = half % timeUnit.numbers.size
        val zeroIndex = half - index - quantityMiddle
        val scrollToIndex =
            zeroIndex + timeUnit.numbers.indexOf(value.toDecimalFormat()).coerceAtLeast(0)

        state.scrollToItem(scrollToIndex)
    }

    // Set correct position when scroll is not in progress and trigger on time change
    LaunchedEffect(state.isScrollInProgress) {
        if (!state.isScrollInProgress) {
            state.animateScrollToItem(state.firstVisibleItemIndex)
            val index = (state.firstVisibleItemIndex + quantityMiddle) % timeUnit.numbers.size
            val number = timeUnit.numbers[index]
            onTimeChange(number)
            hapticInitialized = true
        }
    }
}

@Preview
@Composable
fun PreviewAlarmBox() {
    LeafyTheme {
        var hour by remember { mutableStateOf(14) }
        var minute by remember { mutableStateOf(14) }
        Column {
            TimerBox(
                modifier = Modifier.height(350.dp),
                hour = hour,
                minute = minute,
                onTimeChange = { hr, min ->
                    hour = hr
                    minute = min
                },
            )
            Text("hour: $hour")
            Text("minute: $minute")
        }
    }
}
