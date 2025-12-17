package com.github.mohammadjoshaghani.composescreen.compose.fab

import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.DrawableResource

data class FabIconModel(
    val iconResource: DrawableResource? = null,
    val iconVector: ImageVector? = null,
    val title: String? = null,
    val onFabPressed: () -> Unit,
)