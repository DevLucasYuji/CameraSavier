package com.ujizin.leafy.publish

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ujizin.leafy.core.navigation.AnimatedEnterTransition
import com.ujizin.leafy.core.navigation.AnimatedExitTransition
import com.ujizin.leafy.core.navigation.Destination
import com.ujizin.leafy.core.ui.extensions.OnClick

fun NavGraphBuilder.publishGraph(
    onBackPressed: OnClick,
    onNextClick: OnClick,
    enterTransition: AnimatedEnterTransition = { fadeIn() },
    exitTransition: AnimatedExitTransition = { fadeOut() },
) {
    composable<Destination.Publish>(
        enterTransition = enterTransition,
        exitTransition = exitTransition,
    ) {
        PublishRoute(onBackPressed = onBackPressed, onFinishPublish = onNextClick)
    }
}
