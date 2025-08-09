package com.github.mohammadjoshaghani.composescreen.commonCompose.clickableIcon

import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.DrawableResource

sealed interface IClickableIconModel {
    data class ClickableIconModel(
        val iconId: DrawableResource,
        val badgeCount: Int? = null,
        val onIconPressed: () -> Unit,
    ) : IClickableIconModel

    data class ClickableIconVectorModel(
        val iconId: ImageVector,
        val badgeCount: Int? = null,
        val onIconPressed: () -> Unit,
    ) : IClickableIconModel
}