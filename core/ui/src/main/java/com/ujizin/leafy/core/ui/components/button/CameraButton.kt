package com.ujizin.leafy.core.ui.components.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ujizin.leafy.core.components.R
import com.ujizin.leafy.core.themes.LeafyTheme
import com.ujizin.leafy.core.ui.components.animated.animation.Animate.Animated
import com.ujizin.leafy.core.ui.components.animated.animation.Animation
import com.ujizin.leafy.core.ui.extensions.OnClick

@Composable
internal fun CameraButton(
    modifier: Modifier = Modifier,
    animation: Animation = Animation.SlideToBottom.copy(delayMillis = Animation.MEDIUM_DELAY),
    iconAnimation: Animation = Animation.SlideToTop.copy(delayMillis = Animation.LARGE_DELAY),
    onClick: OnClick,
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Animated(animation = animation) {
            Box(
                modifier =
                    Modifier.clip(CircleShape)
                        .size(72.dp)
                        .background(MaterialTheme.colorScheme.background, CircleShape)
                        .clickable(
                            indication = ripple(bounded = true),
                            onClick = onClick,
                            interactionSource = remember { MutableInteractionSource() },
                        ),
                contentAlignment = Alignment.Center,
            ) {
                Animated(animation = iconAnimation) {
                    Icon(
                        modifier = Modifier.size(32.dp),
                        painter = painterResource(id = R.drawable.camera),
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = null,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewCameraButton() {
    LeafyTheme(dynamicColor = false) {
        CameraButton(animation = Animation.None, iconAnimation = Animation.None) {}
    }
}
