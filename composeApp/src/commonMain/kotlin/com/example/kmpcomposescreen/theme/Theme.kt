package com.example.kmpcomposescreen.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.kmpcomposescreen.theme.color.getApplicationColorScheme

@Composable
fun ExampleTheme(
    content: @Composable (ColorScheme) -> Unit,
) {

    val color = getApplicationColorScheme(false)

    MaterialTheme(
        colorScheme = color,
        shapes = shapes,
        content = { content(color) }
    )
}


