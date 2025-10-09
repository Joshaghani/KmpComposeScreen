package com.github.mohammadjoshaghani.composescreen.compose.fab

import org.jetbrains.compose.resources.DrawableResource

data class FabIconModel(
    val iconResource: DrawableResource? = null,
    val iconVector: DrawableResource? = null,
    val title: String? = null,
    val onFabPressed: () -> Unit,
)