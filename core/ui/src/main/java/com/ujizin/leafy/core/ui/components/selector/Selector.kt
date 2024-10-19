package com.ujizin.leafy.core.ui.components.selector

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ujizin.leafy.core.themes.LeafyTheme
import com.ujizin.leafy.core.ui.components.modal.ModalBottomSheet
import com.ujizin.leafy.core.ui.extensions.capitalize

@Composable
fun Selector(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String? = null,
    value: String? = null,
    showModal: Boolean = false,
    onModalStateChanged: (Boolean) -> Unit = {},
    content: @Composable ColumnScope.() -> Unit,
) {
    ModalBottomSheet(
        showModal = showModal,
        onModalStateChanged = onModalStateChanged,
        content = content,
    )
    ButtonRow(modifier, title, subTitle, value) { onModalStateChanged(true) }
}

@Composable
fun ButtonRow(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String? = null,
    value: String? = null,
    arrowDirection: ArrowDirection = ArrowDirection.Down,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier.clickable(onClick = onClick).then(modifier),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column {
            Text(text = title.capitalize(), style = MaterialTheme.typography.titleSmall)
            if (subTitle != null) {
                Spacer(Modifier.height(4.dp))
                Text(text = subTitle.capitalize())
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (value != null) {
                Text(text = value.capitalize())
            }
            ArrowIcon(
                Modifier.padding(start = 8.dp)
                    .width(arrowDirection.width)
                    .height(arrowDirection.height),
                direction = arrowDirection,
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

enum class ArrowDirection(val width: Dp, val height: Dp) {
    Right(8.dp, 16.dp),
    Down(16.dp, 8.dp),
}

@Composable
private fun ArrowIcon(
    modifier: Modifier = Modifier,
    strokeWidth: Dp = 2.dp,
    color: Color = MaterialTheme.colorScheme.primary,
    direction: ArrowDirection = ArrowDirection.Down,
) {
    Canvas(modifier) {
        val path = Path()
        with(path) {
            moveTo(0F, 0F)
            when (direction) {
                ArrowDirection.Right -> {
                    lineTo(size.width, size.height / 2F)
                    lineTo(0F, size.height)
                }

                ArrowDirection.Down -> {
                    lineTo(size.width / 2F, size.height)
                    lineTo(size.width, 0F)
                }
            }
        }

        drawPath(path, color = color, style = Stroke(strokeWidth.toPx()))
    }
}

@Preview
@Composable
fun PreviewSelector() {
    LeafyTheme {
        Selector(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            title = "ring",
            value = "rang",
            content = { Text("Foo") },
        )
    }
}

@Preview
@Composable
fun PreviewSelectorWithSubtitle() {
    LeafyTheme {
        Selector(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            title = "ring",
            subTitle = "rong",
            value = "rang",
            content = { Text("Foo") },
        )
    }
}

@Preview
@Composable
fun PreviewSelectorWithNoValue() {
    LeafyTheme {
        Selector(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            title = "ring",
            subTitle = "rong",
            content = { Text("Foo") },
        )
    }
}
