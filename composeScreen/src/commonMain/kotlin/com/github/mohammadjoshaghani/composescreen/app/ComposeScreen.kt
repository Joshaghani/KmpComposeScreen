package com.github.mohammadjoshaghani.composescreen.app

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import com.github.mohammadjoshaghani.composescreen.screen.base.IBaseScreen
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig


@Composable
fun ComposeScreen(
    initScreen: List<IBaseScreen>,
    color: ColorScheme = darkColorScheme(),
    isRtl: Boolean = true,
    loadingScreen: (@Composable () -> Unit)? = null,
    errorScreen: @Composable (message: String, retryClick: () -> Unit) -> Unit
) {

    ApplicationConfig.apply {
        this.color = color
        this.isRtl = isRtl
        this.errorScreen = errorScreen
        this.loadingScreen = loadingScreen
    }

    App(initScreen)
}




