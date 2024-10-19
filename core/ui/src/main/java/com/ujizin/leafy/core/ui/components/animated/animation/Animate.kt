package com.ujizin.leafy.core.ui.components.animated.animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier

object Animate {

    enum class Direction {
        None,
        Start,
        Top,
        End,
        Bottom,
    }

    @Composable
    fun Animated(
        modifier: Modifier = Modifier,
        animation: Animation,
        visibleTarget: Boolean = true,
        content: @Composable () -> Unit,
    ) {
        if (animation.direction == Direction.None) {
            content()
            return
        }
        val visible = rememberSaveable { mutableStateOf(false) }
        val visibleState =
            remember(visibleTarget) {
                MutableTransitionState(visible.value).apply {
                    targetState = visibleTarget
                    visible.value = visibleTarget
                }
            }

        AnimatedVisibility(
            modifier = modifier,
            visibleState = visibleState,
            enter =
                animation.enterTransition() +
                    fadeInEaseInOut(animation.durationMillis, animation.delayMillis),
            exit =
                animation.exitTransition() +
                    fadeOutEaseInOut(animation.durationMillis, animation.delayMillis),
        ) {
            content()
        }
    }
}
