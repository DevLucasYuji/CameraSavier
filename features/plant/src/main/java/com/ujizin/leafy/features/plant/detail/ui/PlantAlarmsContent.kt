package com.ujizin.leafy.features.plant.detail.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ujizin.leafy.core.ui.extensions.capitalize
import com.ujizin.leafy.core.ui.extensions.dateFormatted
import com.ujizin.leafy.core.ui.extensions.getDisplayName
import com.ujizin.leafy.domain.model.Alarm
import com.ujizin.leafy.features.plant.R

@Composable
fun PlantAlarmsContent(
    modifier: Modifier = Modifier,
    alarms: List<Alarm>,
    onAlarmChanged: (Alarm) -> Unit,
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.alarms).capitalize(),
            style = MaterialTheme.typography.titleMedium,
        )
        alarms.forEach { alarm ->
            PlantAlarmRow(
                modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                alarm = alarm,
                onCheckedChange = { enabled -> onAlarmChanged(alarm.copy(enabled = enabled)) },
            )
        }
        //        AnimatedButtonIcon( TODO add new alarm
        //            modifier = Modifier
        //                .padding(top = 16.dp)
        //                .size(64.dp)
        //                .clip(CircleShape)
        //                .background(MaterialTheme.colorScheme.secondaryContainer),
        //            icon = Icons.Add,
        //            size = 24.dp,
        //            onClick = {}
        //        )
    }
}

@Composable
private fun PlantAlarmRow(
    modifier: Modifier = Modifier,
    alarm: Alarm,
    onCheckedChange: (Boolean) -> Unit,
) {
    Surface(
        modifier = modifier,
        shadowElevation = 4.dp,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.surface),
        shape = MaterialTheme.shapes.medium,
    ) {
        val context = LocalContext.current
        var checked by remember(alarm.enabled) { mutableStateOf(alarm.enabled) }
        val alphaRow by
            animateFloatAsState(targetValue = if (checked) 1F else 0.5F, label = "PlantAlarmRow")
        Row(
            modifier = Modifier.fillMaxSize().padding(24.dp).alpha(alphaRow),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column {
                Text(
                    text = alarm.dateFormatted,
                    color = MaterialTheme.colorScheme.primary,
                    letterSpacing = 4.sp,
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = alarm.weekDays.getDisplayName(context).capitalize(),
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                )
            }
            Switch(
                checked = checked,
                onCheckedChange = { enabled ->
                    checked = enabled
                    onCheckedChange(enabled)
                },
            )
        }
    }
}
