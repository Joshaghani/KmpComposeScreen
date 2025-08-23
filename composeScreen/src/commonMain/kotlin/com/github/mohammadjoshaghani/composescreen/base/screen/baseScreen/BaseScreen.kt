package com.github.mohammadjoshaghani.composescreen.base.screen.baseScreen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.base.BaseViewModel
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewEvent
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewSideEffect
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewState
import com.github.mohammadjoshaghani.composescreen.base.handler.IScreenInitializer
import com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen.RootScreen
import com.github.mohammadjoshaghani.composescreen.base.screen.baseScreen.compose.ContentScreen
import com.github.mohammadjoshaghani.composescreen.commonCompose.UIAnimatedVisibility
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseScreen<State : ViewState<Event>, Event : ViewEvent, Effect : ViewSideEffect, VM : BaseViewModel<Event, State, Effect>> :
    RootScreen<State, Event, Effect, VM>(), IScreenInitializer<State, Event> {
    var mainScrollState: ScrollState? = null

    private var scrollPositionBaseScreen = mutableIntStateOf(0)

    var isScrolledNow = MutableStateFlow(false)

    var maxHeight: Dp = 0.dp

    @Composable
    override fun ShowScreenFromApp() {
        UIAnimatedVisibility {
            super.SetStateComposeScreen(this)
        }
    }

    @Composable
    override fun InitBaseComposeScreen(state: State) {
        val scrollState = rememberScrollState(scrollPositionBaseScreen.value)
        mainScrollState = scrollState

        LaunchedEffect(scrollState) {
            snapshotFlow {
                scrollState.value > 0
            }.collect { scrolled ->
                isScrolledNow.value = scrolled
                println(scrolled)
            }
        }

        ContentScreen()

    }

    override fun onPause() {
        super.onPause()
        scrollPositionBaseScreen.intValue = mainScrollState?.value ?: 0
    }
}