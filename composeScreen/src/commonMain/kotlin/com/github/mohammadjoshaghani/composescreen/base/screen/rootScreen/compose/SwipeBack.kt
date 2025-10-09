package com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen.compose

import SwipeToGoBackWrapper
import androidx.compose.runtime.Composable
import com.github.mohammadjoshaghani.composescreen.base.handler.IActiveSwipeBackHandler

@Composable
fun WithSwipeBackIfNeeded(
    host: Any,                 // this@RootScreen
    content: @Composable () -> Unit
) {
    if (host is IActiveSwipeBackHandler) {
        SwipeToGoBackWrapper { content() }
    } else content()
}