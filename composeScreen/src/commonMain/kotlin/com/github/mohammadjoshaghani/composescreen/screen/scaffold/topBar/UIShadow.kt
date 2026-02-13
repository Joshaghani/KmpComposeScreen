package com.github.mohammadjoshaghani.composescreen.screen.scaffold.topBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class TypeShadow {
    TOP_BAR,
    BOTTOM_BAR
}

@Composable
fun UIShadow(type: TypeShadow) {
    val isDarkTheme = MaterialTheme.colorScheme.background.luminance() < 0.5
    val (offsetY, height, colors) = when (type) {
        TypeShadow.TOP_BAR -> shadowParams(
            isDarkTheme = isDarkTheme,
            startColor = MaterialTheme.colorScheme.onSurface.copy(alpha = if (isDarkTheme) 0.3f else 0.15f),
            endColor = Color.Transparent,
            offsetLight = 5.dp,
            offsetDark = 1.dp
        )
        TypeShadow.BOTTOM_BAR -> shadowParams(
            isDarkTheme = isDarkTheme,
            startColor = Color.Transparent,
            endColor = MaterialTheme.colorScheme.onSurface.copy(alpha = if (isDarkTheme) 0.3f else 0.15f),
            offsetLight = (-5).dp,
            offsetDark = (-1).dp
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .offset(y = offsetY)
            .background(Brush.verticalGradient(colors))
    )
}

@Composable
private fun shadowParams(
    isDarkTheme: Boolean,
    startColor: Color,
    endColor: Color,
    offsetLight: Dp,
    offsetDark: Dp
): Triple<Dp, Dp, List<Color>> {
    val offset = if (isDarkTheme) offsetDark else offsetLight
    val height = if (isDarkTheme) 1.dp else 5.dp
    val colors = listOf(startColor, endColor)
    return Triple(offset, height, colors)
}
