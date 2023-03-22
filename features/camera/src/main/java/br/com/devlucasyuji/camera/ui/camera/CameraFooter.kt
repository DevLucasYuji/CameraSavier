package br.com.devlucasyuji.camera.ui.camera

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import br.com.devlucasyuji.components.extensions.OnClick
import br.com.devlucasyuji.components.extensions.noClickable
import br.com.devlucasyuji.components.ui.animated.AnimatedIcon
import br.com.devlucasyuji.components.ui.image.Icons

@Composable
fun CameraFooter(
    modifier: Modifier = Modifier,
    onTakePicture: OnClick,
    onGalleryClick: OnClick,
) {
    Box(
        modifier = Modifier
            .noClickable()
            .then(modifier),
    ) {
        ButtonGallery(
            modifier = Modifier.align(Alignment.CenterStart),
            onClick = onGalleryClick,
        )
        ButtonPicture(
            modifier = Modifier.align(Alignment.Center),
            onClick = onTakePicture,
        )
        ButtonSwitchCamera(
            modifier = Modifier.align(Alignment.CenterEnd),
            onClick = {},
        )
    }
}

@Composable
fun ButtonSwitchCamera(
    modifier: Modifier = Modifier,
    onClick: OnClick,
) {
    var rotate by remember { mutableStateOf(false) }
    val rotateAnimation by animateFloatAsState(targetValue = if (rotate) 0F else 180F)
    ButtonPulse(
        modifier = Modifier
            .background(
                MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.25F),
                CircleShape,
            )
            .padding(12.dp)
            .then(modifier),
        shape = CircleShape,
        onClick = {
            rotate = !rotate
            onClick()
        }) {
        AnimatedIcon(
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.Center)
                .rotate(rotateAnimation),
            icon = Icons.Refresh
        )
    }
}

@Composable
fun ButtonGallery(
    modifier: Modifier = Modifier,
    onClick: OnClick,
) {
    ButtonPulse(
        modifier = Modifier
            .background(
                MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.25F),
                CircleShape
            )
            .padding(8.dp)
            .then(modifier),
        shape = CircleShape,
        onClick = onClick
    ) {
        AnimatedIcon(
            modifier = Modifier
                .size(32.dp)
                .align(Alignment.Center),
            icon = Icons.Gallery
        )
    }
}

@Composable
private fun ButtonPicture(
    modifier: Modifier = Modifier,
    onClick: OnClick
) {
    ButtonPulse(
        Modifier
            .size(72.dp)
            .background(MaterialTheme.colorScheme.primary, CircleShape)
            .border(BorderStroke(8.dp, Color.White), CircleShape)
            .then(modifier),
        shape = CircleShape,
        onClick = onClick
    )
}

@Composable
fun ButtonPulse(
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    onClick: OnClick,
    content: @Composable BoxScope.() -> Unit = {},
) {
    val interactionSource = remember { MutableInteractionSource() }
    val buttonPressed by interactionSource.collectIsPressedAsState()
    val scaleAnimated by animateFloatAsState(targetValue = if (buttonPressed) 1.05F else 1F)

    Box(
        modifier = Modifier
            .clip(shape)
            .clickable(
                interactionSource = interactionSource,
                indication = rememberRipple(bounded = true),
                onClick = onClick
            )
            .scale(scaleAnimated)
            .then(modifier),
        content = content
    )
}
