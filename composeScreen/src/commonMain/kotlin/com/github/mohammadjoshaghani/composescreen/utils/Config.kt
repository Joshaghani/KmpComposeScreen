package com.github.mohammadjoshaghani.composescreen.utils

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.DrawableResource

data class Config(
    var appIconId: DrawableResource,
    var color: ColorScheme,
    var isDarkTheme: Boolean = false,
    var isRtl: Boolean = true,
    val errorScreen: @Composable (
        message: String,
        retryClick: () -> Unit,
    ) -> Unit,
    val onBack: () -> Unit = {},
)