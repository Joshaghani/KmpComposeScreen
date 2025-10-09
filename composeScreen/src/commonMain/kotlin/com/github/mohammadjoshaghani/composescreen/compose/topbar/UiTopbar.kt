package com.github.mohammadjoshaghani.composescreen.compose.topbar

import androidx.compose.runtime.Composable

sealed class UiTopbar {
    data class Text(val text: String) : UiTopbar()
    data class Compose(val compose: @Composable () -> Unit) : UiTopbar()
}