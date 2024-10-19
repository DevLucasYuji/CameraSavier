package com.ujizin.leafy.features.plant

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.ujizin.leafy.core.navigation.AnimatedEnterTransition
import com.ujizin.leafy.core.navigation.AnimatedExitTransition
import com.ujizin.leafy.core.navigation.Destination.PlantDetails
import com.ujizin.leafy.core.navigation.Destination.PlantEdit
import com.ujizin.leafy.core.ui.extensions.OnClick
import com.ujizin.leafy.core.ui.extensions.fullSlideInHorizontally
import com.ujizin.leafy.core.ui.extensions.fullSlideOutHorizontally
import com.ujizin.leafy.features.plant.detail.ui.PlantDetailsRoute
import com.ujizin.leafy.features.plant.edit.ui.PlantEditRoute

fun NavGraphBuilder.plantGraph(
    onBackPressed: OnClick,
    enterTransition: AnimatedEnterTransition = { fullSlideInHorizontally() },
    exitTransition: AnimatedExitTransition = { fullSlideOutHorizontally() },
) {
    composable<PlantDetails>(
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        deepLinks = listOf(navDeepLink<PlantDetails>(basePath = PlantDetails.DEEPLINK_URL)),
    ) {
        PlantDetailsRoute(onBackPressed = onBackPressed)
    }
    composable<PlantEdit>(enterTransition = enterTransition, exitTransition = exitTransition) {
        PlantEditRoute(onBackPressed = onBackPressed)
    }
}
