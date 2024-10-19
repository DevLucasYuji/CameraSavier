package com.ujizin.leafy.camera.ui.camera

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ujizin.leafy.core.ui.components.animated.AnimatedButtonIcon
import com.ujizin.leafy.core.ui.components.image.Icons
import com.ujizin.leafy.core.ui.extensions.OnClick

@Composable
internal fun CameraHeader(
    onSettingsClicked: OnClick,
    onFlashModeClicked: OnClick,
    onCloseClicked: OnClick,
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        AnimatedButtonIcon(size = 24.dp, icon = Icons.Back, onClick = onCloseClicked)
        Box() {}
        Box() {}
    }
}
