package com.github.mohammadjoshaghani.composescreen.app

import androidx.compose.runtime.Composable
import com.github.mohammadjoshaghani.composescreen.screen.base.IBaseScreen
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig
import com.github.mohammadjoshaghani.composescreen.utils.Config


@Composable
fun ComposeScreen(
    initScreen: List<IBaseScreen>,
    config: Config,
    loadingScreen: (@Composable () -> Unit)? = null,
    errorScreen: @Composable (message: String, retryClick: () -> Unit) -> Unit
) {

    ApplicationConfig.apply {
        this.config = config
        this.errorScreen = errorScreen
        this.loadingScreen = loadingScreen
    }

    App(initScreen)
}




