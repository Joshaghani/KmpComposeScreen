package com.github.mohammadjoshaghani.composescreen.utils

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.FabPosition
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator

data class Config(
    var color: ColorScheme,
    var isDarkTheme: Boolean = false,
    var isRtl: Boolean = true,
)