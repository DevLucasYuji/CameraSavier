package com.ujizin.leafy.core.ui.components.selector

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ujizin.leafy.core.ui.extensions.capitalize

@Composable
fun <T : Any> ModalSelector(
    modifier: Modifier = Modifier,
    title: String,
    enabled: Boolean = true,
    currentValue: ModalValue<T>,
    values: List<ModalValue<T>>,
    onValueChanged: (ModalValue<T>) -> Unit,
) {
    Column(modifier) {
        Text(
            modifier = Modifier.padding(horizontal = 20.dp),
            text = title.capitalize(),
            style = MaterialTheme.typography.titleMedium,
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(values) { value ->
                ModalItemSelector(
                    Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 16.dp),
                    text = value.name.capitalize(),
                    enabled = enabled,
                    selected = currentValue == value,
                ) {
                    onValueChanged(value)
                }
            }
        }
    }
}

@Composable
fun ModalItemSelector(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean,
    enabled: Boolean = true,
    onItemSelectorClicked: () -> Unit,
) {
    Row(
        modifier =
            Modifier.then(
                    if (enabled) Modifier.clickable(onClick = onItemSelectorClicked) else Modifier
                )
                .then(modifier),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text = text)
        Box(
            modifier =
                Modifier.background(MaterialTheme.colorScheme.primary, CircleShape)
                    .size(24.dp)
                    .padding(8.dp)
                    .then(
                        when {
                            selected ->
                                Modifier.background(
                                    MaterialTheme.colorScheme.onPrimary,
                                    CircleShape,
                                )

                            else -> Modifier
                        }
                    )
        )
    }
}
