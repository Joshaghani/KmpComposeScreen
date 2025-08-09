package com.github.mohammadjoshaghani.composescreen.commonCompose.fab

import org.jetbrains.compose.resources.DrawableResource

data class FabIconModel(
    val iconId: DrawableResource,
    val title: String? = null,
    val onFabPressed: () -> Unit,
)