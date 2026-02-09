package com.github.mohammadjoshaghani.composescreen.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import com.github.mohammadjoshaghani.composescreen.app.RenderDialogs
import com.github.mohammadjoshaghani.composescreen.screen.base.IBaseScreen
import com.github.mohammadjoshaghani.composescreen.screen.base.IBaseScreenImpl
import com.github.mohammadjoshaghani.composescreen.screen.base.IClearStack
import com.github.mohammadjoshaghani.composescreen.screen.swipeBack.SwipeBack
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig

@OptIn(ExperimentalVoyagerApi::class)
abstract class BaseSimpleScreen : Screen, IBaseScreen by IBaseScreenImpl() {

    init {
        setScreen(this)
    }

    @Composable
    override fun Content() {
        LaunchedEffect(Unit) {
            onStart()
        }
        DisposableEffect(Unit) {
            onDispose {
                onStop()
            }
        }

        SwipeBack(this) {
            ComposeView()
        }
        RenderDialogs()
    }

    @Composable
    abstract fun ComposeView()

}


