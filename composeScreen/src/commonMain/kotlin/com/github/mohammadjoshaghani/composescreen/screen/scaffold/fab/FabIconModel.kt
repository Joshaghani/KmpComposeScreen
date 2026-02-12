package com.github.mohammadjoshaghani.composescreen.screen.scaffold.fab

import androidx.compose.material3.FabPosition
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource

data class FabIconModel(
    val customCompose: (@Composable () -> Unit)? = null,
    val iconResource: DrawableResource? = null,
    val iconVector: ImageVector? = null,
    val title: String? = null,
    val onFabPressed: () -> Unit,
    var fabPosition: FabPosition = FabPosition.End,
    var paddingFromStart: Dp = 72.dp
)