package com.ujizin.leafy.core.ui.components.animated.animation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.ui.unit.IntOffset

internal fun Animation.enterTransition(): EnterTransition {
    val animationSpec = tween<IntOffset>(durationMillis, delayMillis, FastOutSlowInEasing)
    return when (direction) {
        Animate.Direction.Start ->
            slideInHorizontally(animationSpec = animationSpec, initialOffsetX = { it / 2 })

        Animate.Direction.Top ->
            slideInVertically(animationSpec = animationSpec, initialOffsetY = { it / 2 })

        Animate.Direction.End -> slideInHorizontally(animationSpec = animationSpec)
        Animate.Direction.Bottom -> slideInVertically(animationSpec = animationSpec)
        Animate.Direction.None -> EnterTransition.None
    }
}

internal fun Animation.exitTransition(): ExitTransition {
    val animationSpec = tween<IntOffset>(durationMillis, delayMillis, FastOutSlowInEasing)
    return when (direction) {
        Animate.Direction.Start ->
            slideOutHorizontally(animationSpec = animationSpec, targetOffsetX = { it * 2 })

        Animate.Direction.Top ->
            slideOutVertically(animationSpec = animationSpec, targetOffsetY = { it * 2 })

        Animate.Direction.End -> slideOutHorizontally(animationSpec = animationSpec)
        Animate.Direction.Bottom -> slideOutVertically(animationSpec = animationSpec)
        Animate.Direction.None -> ExitTransition.None
    }
}

internal fun fadeInEaseInOut(durationMillis: Int, delayMillis: Int) =
    fadeIn(animationSpec = tween(durationMillis, delayMillis, FastOutSlowInEasing))

internal fun fadeOutEaseInOut(durationMillis: Int, delayMillis: Int) =
    fadeOut(animationSpec = tween(durationMillis, delayMillis, FastOutSlowInEasing))
