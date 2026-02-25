package com.github.mohammadjoshaghani.composescreen.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.core.screen.Screen
import com.github.mohammadjoshaghani.composescreen.app.RenderDialogs
import com.github.mohammadjoshaghani.composescreen.screen.base.IBaseScreen
import com.github.mohammadjoshaghani.composescreen.screen.base.IBaseScreenImpl
import com.github.mohammadjoshaghani.composescreen.screen.base.IClearStack
import com.github.mohammadjoshaghani.composescreen.screen.swipeBack.SwipeBack
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig

@OptIn(ExperimentalVoyagerApi::class)
abstract class BaseSimpleScreen : Screen, IBaseScreen by IBaseScreenImpl() {
    private var isStarted = false

    init {
        setScreen(this)
    }

    @Composable
    override fun Content() {
        var restartTrigger by remember { mutableStateOf(0) }

        // وقتی صفحه دوباره به کامپوزیشن میاد (مثلاً بعد از برگشت از صفحه بعدی)
        LaunchedEffect(restartTrigger) {
            if (isStarted) {
                onRestart()
            } else {
                isStarted = true
                onStart()
            }
        }

        // وقتی صفحه از بین میره (مثلاً نَویگیت کردی به صفحه بعدی)
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


