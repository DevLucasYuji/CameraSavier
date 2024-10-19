package com.ujizin.leafy.core.navigation

import androidx.compose.animation.AnimatedContentTransitionScope as TransitionScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDeepLink
import androidx.navigation.NavDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

typealias AnimatedEnterTransition = (TransitionScope<NavBackStackEntry>.() -> EnterTransition?)

typealias AnimatedExitTransition = (TransitionScope<NavBackStackEntry>.() -> ExitTransition?)

inline fun <reified T : Destination, A> NavGraphBuilder.composable(
    noinline enterTransition: AnimatedEnterTransition = { fadeIn() },
    noinline exitTransition: AnimatedExitTransition = { fadeOut() },
    noinline popEnterTransition: AnimatedEnterTransition? = enterTransition,
    noinline popExitTransition: AnimatedExitTransition? = exitTransition,
    deepLinks: List<NavDeepLink> = emptyList(),
    noinline content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit,
) {
    composable<T>(
        deepLinks = deepLinks,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
        content = content,
    )
}

fun NavController.navigateUp(times: Int) {
    repeat(times) { navigateUp() }
}

val NavDestination.qualifiedRoute: String?
    get() =
        route
            ?.split("?") // Remove queries parameter
            ?.firstOrNull()
