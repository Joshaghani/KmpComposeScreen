package com.example.kmpcomposescreen.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.runtime.Composable
import com.example.kmpcomposescreen.theme.color.getApplicationColorScheme

@Composable
fun ExampleTheme(
    content: @Composable () -> Unit,
) {

    val color = getApplicationColorScheme(false)

    MaterialTheme(
        colorScheme = color,
        shapes = shapes,
        content = content
    )
}


