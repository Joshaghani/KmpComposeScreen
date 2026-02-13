package com.github.mohammadjoshaghani.composescreen.screen.scaffold.topBar

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.ui.unit.dp


@Composable
fun TopBarShadow() {

    val isDarkTheme = isDarkFromColorScheme()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(if (isDarkTheme) 1.dp else 5.dp)
            .offset(y = if (isDarkTheme) 1.dp else 5.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.onSurface.copy(alpha = if (isDarkTheme) 0.3f else 0.15f),
                        Color.Transparent
                    )
                )
            )
    )
}

@Composable
fun BottomBarShadow() {
    val isDarkTheme = isDarkFromColorScheme()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = if (isDarkTheme) (-1).dp else (-5).dp)
            .height(if (isDarkTheme) 1.dp else 5.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        MaterialTheme.colorScheme.onSurface.copy(alpha = if (isDarkTheme) 0.3f else 0.15f),
                    )
                )
            )
    )
}

@Composable
fun isDarkFromColorScheme(): Boolean {
    val colorScheme = MaterialTheme.colorScheme
    return colorScheme.background.luminance() < 0.5
}