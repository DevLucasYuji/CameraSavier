package com.ujizin.leafy.core.ui.extensions

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.innerShadow(colors: List<Color>, shape: Shape = RectangleShape) =
    then(Modifier.background(brush = Brush.verticalGradient(colors), shape = shape))

fun Modifier.paddingScreen(horizontal: Dp = 20.dp, vertical: Dp = 0.dp) =
    then(Modifier.padding(horizontal = horizontal, vertical = vertical))

fun Modifier.noClickable() = then(Modifier.clickable(enabled = false, onClick = {}))
