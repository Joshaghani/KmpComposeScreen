package com.github.mohammadjoshaghani.composescreen.screen.scaffold.topBar

import androidx.compose.runtime.Composable

sealed interface TopbarTypeTitle {
    data class Text(val title: String) : TopbarTypeTitle
    data class Compose(val content: @Composable () -> Unit) : TopbarTypeTitle
    data object Nothing : TopbarTypeTitle
}