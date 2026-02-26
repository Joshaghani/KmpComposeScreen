package com.github.mohammadjoshaghani.composescreen.screen.toast

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.github.mohammadjoshaghani.composescreen.app.RenderDialogs
import com.github.mohammadjoshaghani.composescreen.screen.contract.ViewState

@Composable
fun <StateT : ViewState<*>> ScreenSideEffects(
    state: StateT,
    clearToast: () -> Unit
) {
    val toastMessage = state.toastMessage
    LaunchedEffect(toastMessage) {
        if (toastMessage != null) {
            ToastCreator.showToast(toastMessage)
            clearToast()
        }
    }
    RenderDialogs()
}