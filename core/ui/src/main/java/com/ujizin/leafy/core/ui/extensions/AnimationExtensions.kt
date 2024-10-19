package com.ujizin.leafy.core.ui.extensions

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Stable

@Stable fun fullSlideInHorizontally() = slideInHorizontally { it }

@Stable fun fullSlideOutHorizontally() = slideOutHorizontally { it }

@Stable fun fullSlideInVertically() = slideInVertically { it }

@Stable fun fullSlideOutVertically() = slideOutVertically { -it }
