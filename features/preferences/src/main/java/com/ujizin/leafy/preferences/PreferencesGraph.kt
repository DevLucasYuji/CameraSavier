package com.ujizin.leafy.preferences

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.ujizin.leafy.core.navigation.AnimatedEnterTransition
import com.ujizin.leafy.core.navigation.AnimatedExitTransition
import com.ujizin.leafy.core.navigation.Destination
import com.ujizin.leafy.core.navigation.composable
import com.ujizin.leafy.core.ui.extensions.OnClick
import com.ujizin.leafy.preferences.ui.Preferences

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.preferencesGraph(
    enterTransition: AnimatedEnterTransition,
    exitTransition: AnimatedExitTransition,
    onBackPressed: OnClick,
) {
    composable(
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        destination = Destination.Preferences,
    ) {
        Preferences(onBackPressed = onBackPressed)
    }
}
