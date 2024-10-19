package com.ujizin.leafy.about

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ujizin.leafy.core.navigation.Destination
import com.ujizin.leafy.core.ui.extensions.OnClick
import com.ujizin.leafy.core.ui.extensions.fullSlideInVertically
import com.ujizin.leafy.core.ui.extensions.fullSlideOutVertically

fun NavGraphBuilder.aboutGraph(onBackPressed: OnClick) {
    composable<Destination.About>(
        enterTransition = { fullSlideInVertically() },
        exitTransition = { fullSlideOutVertically() },
    ) {
        AboutRoute(onBackPressed = onBackPressed)
    }
}
