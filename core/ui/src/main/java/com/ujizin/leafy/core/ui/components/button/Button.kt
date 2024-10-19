package com.ujizin.leafy.core.ui.components.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button as Material3Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ujizin.leafy.core.themes.LeafyTheme
import com.ujizin.leafy.core.ui.components.animated.AnimatedText
import com.ujizin.leafy.core.ui.extensions.Content
import com.ujizin.leafy.core.ui.extensions.OnClick

@Composable
fun Button(
    modifier: Modifier = Modifier,
    text: String? = null,
    enabled: Boolean = true,
    onClick: OnClick,
    capitalize: Boolean = true,
    shape: Shape = RoundedCornerShape(4.dp),
    contentPadding: PaddingValues = PaddingValues(horizontal = 24.dp, vertical = 16.dp),
    content: @Composable Content = {},
) {
    Material3Button(
        enabled = enabled,
        modifier = modifier,
        shape = shape,
        contentPadding = contentPadding,
        onClick = onClick,
    ) {
        text?.let { text ->
            val contentColor by
                rememberUpdatedState(
                    newValue =
                        when {
                            enabled -> MaterialTheme.colorScheme.onPrimary
                            else -> MaterialTheme.colorScheme.onSurface
                        }
                )
            AnimatedText(color = contentColor, capitalize = capitalize, text = text)
        } ?: content()
    }
}

@Preview
@Composable
fun PreviewButton() {
    LeafyTheme { Button(text = "Hello world!", onClick = {}) }
}
