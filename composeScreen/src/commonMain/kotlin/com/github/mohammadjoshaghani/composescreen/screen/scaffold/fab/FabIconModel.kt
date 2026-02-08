package com.github.mohammadjoshaghani.composescreen.screen.scaffold.fab

import androidx.compose.material3.FabPosition
import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.DrawableResource

data class FabIconModel(
    val iconResource: DrawableResource? = null,
    val iconVector: ImageVector? = null,
    val title: String? = null,
    val onFabPressed: () -> Unit,
    var fabPosition: FabPosition = FabPosition.End,
)