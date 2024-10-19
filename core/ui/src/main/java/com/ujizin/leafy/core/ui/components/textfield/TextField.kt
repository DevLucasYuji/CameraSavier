package com.ujizin.leafy.core.ui.components.textfield

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField as MaterialTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ujizin.leafy.core.themes.LeafyTheme
import com.ujizin.leafy.core.ui.components.animated.animation.Animate.Animated
import com.ujizin.leafy.core.ui.components.animated.animation.Animation
import com.ujizin.leafy.core.ui.extensions.Content
import com.ujizin.leafy.core.ui.extensions.capitalize

@Composable
fun TextField(
    modifier: Modifier = Modifier,
    value: String,
    singleLine: Boolean = true,
    shape: Shape = RoundedCornerShape(4.dp),
    animation: Animation = Animation.SlideToTop,
    placeholder: @Composable Content? = null,
    onValueChange: (String) -> Unit,
) {
    Animated(animation = animation) {
        MaterialTextField(
            modifier = modifier,
            value = value,
            onValueChange = onValueChange,
            singleLine = singleLine,
            shape = shape,
            placeholder = placeholder,
            textStyle = LocalTextStyle.current,
            colors =
                TextFieldDefaults.colors(
                    cursorColor = MaterialTheme.colorScheme.primary,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
        )
    }
}

@Composable
fun Placeholder(
    modifier: Modifier = Modifier,
    leadingIcon: @Composable Content = {},
    text: String,
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        leadingIcon()

        Text(
            text = text.capitalize(),
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5F),
        )
    }
}

@Preview
@Composable
private fun PreviewTextField() {
    LeafyTheme {
        var value by remember { mutableStateOf("") }
        TextField(value = value, onValueChange = { value = it })
    }
}
