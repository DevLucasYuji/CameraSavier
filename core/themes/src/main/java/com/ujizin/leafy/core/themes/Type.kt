package com.ujizin.leafy.core.themes

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Typography
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val PoppinsFamily =
    FontFamily(
        Font(R.font.poppins_bold, FontWeight.Bold),
        Font(R.font.poppins_regular, FontWeight.Normal),
    )

internal fun getTypography(color: ColorScheme) =
    Typography(
        titleLarge =
            TextStyle(
                fontFamily = PoppinsFamily,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = color.onBackground,
                platformStyle = PlatformTextStyle(includeFontPadding = false),
            ),
        titleMedium =
            TextStyle(
                fontFamily = PoppinsFamily,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = color.onBackground,
                platformStyle = PlatformTextStyle(includeFontPadding = false),
            ),
        titleSmall =
            TextStyle(
                fontFamily = PoppinsFamily,
                fontSize = 16.sp,
                color = color.onBackground,
                fontWeight = FontWeight.Bold,
                platformStyle = PlatformTextStyle(includeFontPadding = false),
            ),
        bodyMedium =
            TextStyle(
                fontFamily = PoppinsFamily,
                fontSize = 16.sp,
                color = color.onBackground,
                platformStyle = PlatformTextStyle(includeFontPadding = false),
            ),
    )
