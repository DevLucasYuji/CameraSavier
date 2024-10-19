package com.ujizin.leafy.camera

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.Spring.DampingRatioNoBouncy
import androidx.compose.animation.core.Spring.StiffnessLow
import androidx.compose.animation.core.Spring.StiffnessMediumLow
import androidx.compose.animation.core.spring
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ujizin.leafy.camera.ui.CameraRoute
import com.ujizin.leafy.core.navigation.Destination
import com.ujizin.leafy.core.ui.extensions.OnClick

fun NavGraphBuilder.cameraGraph(onBackPressed: OnClick, onSaveClicked: () -> Unit) {
    composable<Destination.Camera>(
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Up,
                animationSpec = spring(DampingRatioNoBouncy, StiffnessLow),
                initialOffset = { it * 2 },
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Down,
                animationSpec = spring(DampingRatioNoBouncy, StiffnessMediumLow),
                targetOffset = { -it },
            )
        },
    ) {
        CameraRoute(onCloseClicked = onBackPressed, onImageSaved = onSaveClicked)
    }
}
