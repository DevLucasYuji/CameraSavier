package com.ujizin.leafy.core.ui.components.dropdown

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ujizin.leafy.core.ui.components.button.Button
import com.ujizin.leafy.core.ui.extensions.capitalize

@Composable
fun WarningContent(
    modifier: Modifier = Modifier,
    title: String,
    text: String,
    onDismissText: String,
    onConfirmText: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    Column(modifier) {
        Text(text = title.capitalize(), style = MaterialTheme.typography.titleMedium)
        Text(modifier = Modifier.padding(top = 16.dp), text = text.capitalize())
        Row(
            modifier = Modifier.padding(top = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            OutlinedButton(
                modifier = Modifier.weight(1F),
                content = { Text(text = onDismissText.capitalize()) },
                shape = RoundedCornerShape(4.dp),
                contentPadding = PaddingValues(16.dp),
                onClick = onDismiss,
            )
            Button(
                modifier = Modifier.weight(1F),
                text = onConfirmText.capitalize(),
                onClick = onConfirm,
            )
        }
    }
}
