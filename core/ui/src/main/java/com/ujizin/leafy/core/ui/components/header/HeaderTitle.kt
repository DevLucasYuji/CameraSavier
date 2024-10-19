package com.ujizin.leafy.core.ui.components.header

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.ujizin.leafy.core.themes.LeafyTheme
import com.ujizin.leafy.core.ui.components.animated.AnimatedText
import com.ujizin.leafy.core.ui.components.animated.animation.Animation
import com.ujizin.leafy.core.ui.extensions.Empty

@Composable
fun HeaderTitle(
    modifier: Modifier = Modifier,
    animation: Animation = Animation.SlideToTop,
    title: String,
    titleStyle: TextStyle = MaterialTheme.typography.titleLarge,
    subTitle: String = String.Empty,
    subTitleStyle: TextStyle = MaterialTheme.typography.bodyMedium,
) {
    Column(modifier) {
        AnimatedText(
            animation = animation,
            text = title,
            style = titleStyle,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
        if (subTitle != String.Empty) {
            AnimatedText(
                animation = animation.copy(delayMillis = animation.durationMillis),
                text = subTitle,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = subTitleStyle,
            )
        }
    }
}

@Preview("Header Title with Subtitle")
@Composable
private fun PreviewHeaderTitleWithSubtitle() {
    LeafyTheme {
        HeaderTitle(title = "Hi Lucas", subTitle = "Welcome back!", animation = Animation.None)
    }
}

@Preview("Header Title")
@Composable
private fun PreviewHeaderTitle() {
    LeafyTheme { HeaderTitle(title = "Hi Lucas", animation = Animation.None) }
}
