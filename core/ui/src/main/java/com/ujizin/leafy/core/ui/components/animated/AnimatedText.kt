package com.ujizin.leafy.core.ui.components.animated

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.ujizin.leafy.core.themes.LeafyTheme
import com.ujizin.leafy.core.ui.components.animated.animation.Animate.Animated
import com.ujizin.leafy.core.ui.components.animated.animation.Animation
import com.ujizin.leafy.core.ui.extensions.capitalize

@Composable
fun AnimatedText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Unspecified,
    capitalize: Boolean = true,
    style: TextStyle = LocalTextStyle.current,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    animation: Animation = Animation.None,
) {
    Animated(animation = animation) {
        Text(
            text = if (capitalize) text.capitalize() else text,
            modifier = modifier,
            maxLines = maxLines,
            overflow = overflow,
            color = color,
            style = style,
        )
    }
}

@Preview
@Composable
private fun PreviewTitle() {
    LeafyTheme { AnimatedText(text = "Hello Lucas") }
}
