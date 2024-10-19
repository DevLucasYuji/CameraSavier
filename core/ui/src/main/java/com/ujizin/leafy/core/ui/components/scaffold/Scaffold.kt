package com.ujizin.leafy.core.ui.components.scaffold

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ujizin.leafy.core.ui.extensions.Content

@Composable
fun Scaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable Content,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(modifier, content = content)
    Box(modifier = Modifier.padding(24.dp)) { topBar() }
}

@Composable
fun TopAppBar(
    onNavigationContent: @Composable Content,
    onActionContent: @Composable RowScope.() -> Unit = {},
) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        onNavigationContent()
        Row { onActionContent() }
    }
}
