package com.github.mohammadjoshaghani.composescreen.screen.scaffold.footer

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp

data class FooterModel(
    val height: Dp,
    val content: @Composable () -> Unit
)