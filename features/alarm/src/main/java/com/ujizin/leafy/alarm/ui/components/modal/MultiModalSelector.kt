package com.ujizin.leafy.alarm.ui.components.modal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ujizin.leafy.core.ui.components.button.Button
import com.ujizin.leafy.core.ui.components.selector.ModalItemSelector
import com.ujizin.leafy.core.ui.extensions.capitalize
import com.ujizin.leafy.core.ui.extensions.paddingScreen
import com.ujizin.leafy.features.alarm.R

// TODO set on core:ui module
@Composable
internal fun <T : Any> MultiModalSelector(
    modifier: Modifier = Modifier,
    title: String,
    currentValues: List<ModalValue<T>>,
    values: List<ModalValue<T>>,
    onValuesSelected: (List<ModalValue<T>>) -> Unit,
) {
    val selectedValues =
        remember(currentValues) { mutableStateListOf(*currentValues.toTypedArray()) }

    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(horizontal = 20.dp),
            text = title.capitalize(),
            style = MaterialTheme.typography.titleMedium,
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(values) { item ->
                ModalItemSelector(
                    Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 16.dp),
                    text = item.name.capitalize(),
                    selected = selectedValues.contains(item),
                    onItemSelectorClicked = {
                        with(selectedValues) { if (contains(item)) remove(item) else add(item) }
                    },
                )
            }
        }
        Button(
            modifier = Modifier.fillMaxWidth().paddingScreen(vertical = 16.dp),
            text = stringResource(R.string.save),
            enabled = selectedValues.isNotEmpty(),
            onClick = { onValuesSelected(selectedValues) },
        )
    }
}
