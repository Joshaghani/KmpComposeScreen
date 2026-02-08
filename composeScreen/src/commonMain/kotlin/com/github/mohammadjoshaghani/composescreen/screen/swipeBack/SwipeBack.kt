package com.github.mohammadjoshaghani.composescreen.screen.swipeBack

import androidx.compose.runtime.Composable

@Composable
fun SwipeBack(
    host: Any,                 // this@RootScreen
    content: @Composable () -> Unit
) {
    if (host is IActiveSwipeBackHandler) {
        SwipeToGoBackWrapper { content() }
    } else content()
}