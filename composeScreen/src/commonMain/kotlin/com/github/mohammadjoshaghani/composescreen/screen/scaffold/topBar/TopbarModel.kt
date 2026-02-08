package com.github.mohammadjoshaghani.composescreen.screen.scaffold.topBar

import androidx.compose.runtime.Composable

sealed interface TopbarModel {
    data class Text(val title: String) : TopbarModel
    data class Compose(val content: @Composable () -> Unit) : TopbarModel
    data object Nothing : TopbarModel
}