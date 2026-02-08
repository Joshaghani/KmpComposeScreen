package com.github.mohammadjoshaghani.composescreen.component

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun ObserveWindowSizeChange(onSizeClassChanged: (WindowSizeClass) -> Unit) {
    var previousSizeClass by remember { mutableStateOf<WindowSizeClass?>(null) }
    val currentSizeClass = calculateWindowSizeClass()

    LaunchedEffect(currentSizeClass) {
        if (currentSizeClass != previousSizeClass) {
            previousSizeClass = currentSizeClass
            onSizeClassChanged(currentSizeClass)
        }
    }
}