package com.ujizin.leafy.core.ui.components.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.ujizin.leafy.core.ui.extensions.Content
import com.ujizin.leafy.core.ui.extensions.innerShadow
import com.ujizin.leafy.core.ui.props.Shadow

@Composable
fun BoxImage(
    modifier: Modifier = Modifier,
    painter: Painter,
    contentDescription: String?,
    contentAlignment: Alignment = Alignment.TopStart,
    contentScale: ContentScale = ContentScale.Crop,
    content: @Composable Content,
) {
    Box(modifier) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painter,
            contentScale = contentScale,
            contentDescription = contentDescription,
        )
        Box(
            modifier =
                Modifier.fillMaxSize()
                    .innerShadow(Shadow.Black, RoundedCornerShape(4.dp))
                    .padding(12.dp),
            contentAlignment = contentAlignment,
        ) {
            content()
        }
    }
}
