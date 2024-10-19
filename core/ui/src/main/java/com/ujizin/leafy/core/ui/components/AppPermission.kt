package com.ujizin.leafy.core.ui.components

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.ujizin.leafy.core.ui.extensions.Content

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AppPermission(content: @Composable Content) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val permissionState =
            rememberPermissionState(permission = android.Manifest.permission.POST_NOTIFICATIONS)

        LaunchedEffect(permissionState.status) {
            if (permissionState.status is PermissionStatus.Denied) {
                permissionState.launchPermissionRequest()
            }
        }
    }

    content()
}
