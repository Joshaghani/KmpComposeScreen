package com.github.mohammadjoshaghani.composescreen.screen.scaffold.topBar

import androidx.compose.runtime.Composable

sealed interface TopbarTypeCompose {
    data class Text(val title: String) : TopbarTypeCompose
    data class Compose(val content: @Composable () -> Unit) : TopbarTypeCompose
    data object Nothing : TopbarTypeCompose
}