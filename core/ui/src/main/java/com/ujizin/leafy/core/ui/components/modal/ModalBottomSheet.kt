package com.ujizin.leafy.core.ui.components.modal

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ModalBottomSheet(
    showModal: Boolean,
    skipPartiallyExpanded: Boolean = false,
    onModalStateChanged: (Boolean) -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    var isAnimating by remember(Unit) { mutableStateOf(showModal) }
    val modalState = rememberModalBottomSheetState(skipPartiallyExpanded = skipPartiallyExpanded)

    LaunchedEffect(showModal) {
        launch { if (showModal) modalState.show() else modalState.hide() }
            .invokeOnCompletion { isAnimating = showModal }
    }

    if (showModal || isAnimating) {
        androidx.compose.material3.ModalBottomSheet(
            sheetState = modalState,
            onDismissRequest = { onModalStateChanged(false) },
            content = content,
        )
    }
}
