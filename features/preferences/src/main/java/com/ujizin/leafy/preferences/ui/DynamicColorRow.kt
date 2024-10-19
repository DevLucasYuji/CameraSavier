package com.ujizin.leafy.preferences.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ujizin.leafy.core.ui.extensions.capitalize
import com.ujizin.leafy.features.preferences.R

@Composable
fun DynamicColorRow(
    modifier: Modifier = Modifier,
    dynamicColor: Boolean,
    onDynamicColorChanged: (Boolean) -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = stringResource(id = R.string.dynamic_color).capitalize(),
            style = MaterialTheme.typography.titleSmall,
        )

        Switch(checked = dynamicColor, onCheckedChange = onDynamicColorChanged)
    }
}
