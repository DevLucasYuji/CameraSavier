package com.ujizin.leafy.camera.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ujizin.leafy.core.components.R
import com.ujizin.leafy.core.ui.components.EmptySection
import com.ujizin.leafy.core.ui.components.animated.AnimatedButtonIcon
import com.ujizin.leafy.core.ui.components.image.Icons
import com.ujizin.leafy.core.ui.extensions.OnClick
import com.ujizin.leafy.core.ui.extensions.capitalize
import com.ujizin.leafy.core.ui.extensions.paddingScreen

@Composable
internal fun CameraDenied(
    shouldShowRationale: Boolean,
    onBackPressed: () -> Unit,
    onCameraRequest: OnClick,
) {
    val descriptionRes by
        remember(shouldShowRationale) {
            mutableIntStateOf(
                when {
                    shouldShowRationale -> R.string.camera_permission_description
                    else -> R.string.camera_permission_denied_description
                }
            )
        }
    AnimatedButtonIcon(
        modifier = Modifier.paddingScreen(vertical = 24.dp),
        icon = Icons.Back,
        onClick = onBackPressed,
    )
    EmptySection(
        modifier = Modifier.fillMaxSize(),
        description = stringResource(descriptionRes).capitalize(),
        buttonTitle = stringResource(R.string.camera_permission_allow).capitalize(),
        icons = Icons.Folder,
        onClick = onCameraRequest,
    )
}
