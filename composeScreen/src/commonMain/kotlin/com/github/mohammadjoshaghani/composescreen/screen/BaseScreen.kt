package com.github.mohammadjoshaghani.composescreen.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import com.github.mohammadjoshaghani.composescreen.app.RenderDialogs
import com.github.mohammadjoshaghani.composescreen.screen.base.IBaseScreen
import com.github.mohammadjoshaghani.composescreen.screen.base.IBaseScreenImpl
import com.github.mohammadjoshaghani.composescreen.screen.base.IClearStack
import com.github.mohammadjoshaghani.composescreen.screen.contract.ViewEvent
import com.github.mohammadjoshaghani.composescreen.screen.contract.ViewSideEffect
import com.github.mohammadjoshaghani.composescreen.screen.contract.ViewState
import com.github.mohammadjoshaghani.composescreen.screen.swipeBack.SwipeBack
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig

@OptIn(ExperimentalVoyagerApi::class)
abstract class BaseScreen<
        STATE : ViewState<EVENT>,
        EVENT : ViewEvent,
        EFFECT : ViewSideEffect,
        VM : BaseViewModel<STATE, EVENT, EFFECT>> :
    Screen, IBaseScreen by IBaseScreenImpl() {

    abstract val viewModel: VM
    abstract val handler: BaseHandler<EVENT, EFFECT, VM>

    var onEventSent: (EVENT) -> Unit = { viewModel.handleEvents(it) }

    init {
        setScreen(this)
    }

    @Composable
    override fun Content() {
        LaunchedEffect(viewModel) {
            viewModel.effect.collect { handler.handleEffects(it, viewModel) }
        }

        LaunchedEffect(Unit) {
            onStart()
        }

        DisposableEffect(Unit) {
            onDispose {
                onStop()
            }
        }

        when {
            viewModel.state.value.isLoading -> ShowLoadingIndicator()
            viewModel.state.value.errorScreen != null -> {
                ShowErrorScreen()
            }

            else -> {
                SwipeBack(this) {
                    ComposeView(viewModel.state.value)
                }
                RenderDialogs()
            }
        }
    }

    @Composable
    private fun ShowLoadingIndicator() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            ApplicationConfig.loadingScreen?.let { screen ->
                screen()
            } ?: run {
                CircularProgressIndicator()
            }
        }
    }

    @Composable
    private fun ShowErrorScreen() {
        val errorScreen = viewModel.state.value.errorScreen!!
        ApplicationConfig.errorScreen(errorScreen.message) {
            viewModel.handleEvents(errorScreen.event)
        }
    }

    @Composable
    abstract fun ComposeView(state: STATE)

}


