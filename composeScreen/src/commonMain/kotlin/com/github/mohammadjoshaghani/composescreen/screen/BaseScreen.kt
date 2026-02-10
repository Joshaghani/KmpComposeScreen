package com.github.mohammadjoshaghani.composescreen.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.koin.koinScreenModel
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

    private lateinit var viewModel: VM

    var onEventSent: (EVENT) -> Unit = { viewModel.handleEvents(it) }

    @Composable
    abstract fun getViewModel(): VM
    abstract val handler: BaseHandler<EVENT, EFFECT, VM>

    init {
        setScreen(this)
    }

    @Composable
    override fun Content() {

        viewModel = getViewModel()
        val viewState by viewModel.state.collectAsState()

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
            viewState.isLoading -> ShowLoadingIndicator()
            viewState.errorScreen != null -> {
                ShowErrorScreen(viewModel, viewState)
            }

            else -> {
                SwipeBack(this) {
                    ComposeView(viewState)
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
    private fun ShowErrorScreen(viewModel: VM, state: STATE) {
        val errorScreen = state.errorScreen!!
        ApplicationConfig.errorScreen(errorScreen.message) {
            viewModel.handleEvents(errorScreen.event)
        }
    }

    @Composable
    abstract fun ComposeView(state: STATE)

}

