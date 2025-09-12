package com.github.mohammadjoshaghani.composescreen.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.backhandler.BackHandler
import com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen.RootScreen
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig
import com.github.mohammadjoshaghani.composescreen.utils.Config

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ComposeScreen(
    startScreen: RootScreen<*, *, *, *>,
    config: Config,
) {
    ApplicationConfig.config = config
    AppContent(startScreen)
    BackHandler(onBack = config.onBack)
}







