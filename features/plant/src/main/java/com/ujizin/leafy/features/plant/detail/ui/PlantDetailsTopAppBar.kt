package com.ujizin.leafy.features.plant.detail.ui

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ujizin.leafy.core.ui.components.animated.AnimatedButtonIcon
import com.ujizin.leafy.core.ui.components.animated.animation.Animation
import com.ujizin.leafy.core.ui.components.dropdown.WarningContent
import com.ujizin.leafy.core.ui.components.image.Icons
import com.ujizin.leafy.core.ui.components.modal.ModalBottomSheet
import com.ujizin.leafy.core.ui.extensions.OnClick
import com.ujizin.leafy.core.ui.extensions.capitalize
import com.ujizin.leafy.core.ui.extensions.paddingScreen
import com.ujizin.leafy.features.plant.R

@Composable
internal fun PlantDropDown(
    onDeleteClick: OnClick,
    modifier: Modifier = Modifier,
    animation: Animation = Animation.None,
) {
    var isExpanded by remember { mutableStateOf(false) }
    var showDeleteModal by remember { mutableStateOf(false) }
    AnimatedDropDownMenu(
        modifier = modifier,
        isExpanded = isExpanded,
        icon = Icons.Dropdown,
        animation = animation,
        onExpandedChanged = { isExpanded = it },
    ) {
        DropdownMenuItem(
            text = { Text(stringResource(R.string.delete).capitalize()) },
            onClick = {
                isExpanded = false
                showDeleteModal = true
            },
        )
    }

    ModalBottomSheet(
        showModal = showDeleteModal,
        onModalStateChanged = { showDeleteModal = it },
        content = {
            WarningContent(
                modifier = Modifier.paddingScreen().padding(bottom = 24.dp),
                title = stringResource(R.string.warning_delete_title),
                text = stringResource(R.string.warning_delete_text),
                onDismissText = stringResource(R.string.warning_delete_dismiss),
                onConfirmText = stringResource(R.string.warning_delete_confirm),
                onDismiss = { showDeleteModal = false },
                onConfirm = {
                    showDeleteModal = false
                    onDeleteClick()
                },
            )
        },
    )
}

@Composable
private fun AnimatedDropDownMenu(
    modifier: Modifier = Modifier,
    icon: Icons,
    isExpanded: Boolean,
    animation: Animation = Animation.None,
    onExpandedChanged: (Boolean) -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    AnimatedButtonIcon(
        modifier = modifier,
        icon = icon,
        size = 24.dp,
        animation = animation,
        onClick = { onExpandedChanged(true) },
    )
    DropdownMenu(
        modifier = Modifier.fillMaxWidth(0.5F),
        expanded = isExpanded,
        onDismissRequest = { onExpandedChanged(false) },
        content = content,
    )
}
