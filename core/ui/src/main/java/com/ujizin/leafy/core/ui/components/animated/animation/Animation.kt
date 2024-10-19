package com.ujizin.leafy.core.ui.components.animated.animation

data class Animation
internal constructor(
    val direction: Animate.Direction,
    val delayMillis: Int = 0,
    val durationMillis: Int = MEDIUM_DURATION,
) {
    companion object {
        const val SMALL_DURATION = 500
        const val MEDIUM_DELAY = 500
        const val LARGE_DELAY = 1000
        private const val MEDIUM_DURATION = 750
        private const val LARGE_DURATION = 1_000

        // Default animations
        val None = Animation(Animate.Direction.None)
        val SlideToStart = Animation(Animate.Direction.Start)
        val SlideToTop = Animation(Animate.Direction.Top)
        val SlideToEnd = Animation(Animate.Direction.End)
        val SlideToBottom = Animation(Animate.Direction.Bottom)
        val SlideToTopLargeDuration = SlideToTop.copy(durationMillis = LARGE_DURATION)
    }
}
