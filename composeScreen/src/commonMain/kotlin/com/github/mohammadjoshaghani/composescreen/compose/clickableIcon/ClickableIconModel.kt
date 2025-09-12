package com.github.mohammadjoshaghani.composescreen.compose.clickableIcon

import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.DrawableResource

sealed interface IClickableIconModel {
    data class ClickableIconModel(
        val iconId: DrawableResource,
        val title: String? = null,
        val badgeCount: Int? = null,
        val onIconPressed: () -> Unit,
    ) : IClickableIconModel

    data class ClickableIconVectorModel(
        val iconId: ImageVector,
        val title: String? = null,
        val badgeCount: Int? = null,
        val onIconPressed: () -> Unit,
    ) : IClickableIconModel
}