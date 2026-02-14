package com.github.mohammadjoshaghani.composescreen.bottomSheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import com.github.mohammadjoshaghani.composescreen.screen.BaseHandler
import com.github.mohammadjoshaghani.composescreen.screen.BaseViewModel
import com.github.mohammadjoshaghani.composescreen.screen.contract.ViewEvent
import com.github.mohammadjoshaghani.composescreen.screen.contract.ViewSideEffect
import com.github.mohammadjoshaghani.composescreen.screen.contract.ViewState
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig

abstract class BaseBottomSheet<State : ViewState<Event>, Event : ViewEvent, Effect : ViewSideEffect, VM : BaseViewModel<State, Event, Effect>> :
    Screen {

    abstract val viewModel: VM
    abstract val handler: BaseHandler<Event, Effect, VM>

    var onEventSent: (Event) -> Unit = { viewModel.handleEvents(it) }

    fun show() {
        ApplicationConfig.bottomSheetNavigator?.show(this)
    }

    @Composable
    override fun Content() {

        LaunchedEffect(viewModel) {
            viewModel.effect.collect { handler.handleEffects(it, viewModel) }
        }
        when {
            viewModel.state.value.isLoading -> ShowLoadingIndicator()
            viewModel.state.value.errorScreen != null -> {
                ShowErrorScreen()
            }

            else -> {
                Column(
                    Modifier
                        .background(MaterialTheme.colorScheme.surface)
                        .navigationBarsPadding()

                ) {
                    ComposeView(viewModel.state.value)
                }
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
    abstract fun ComposeView(state: State)


}