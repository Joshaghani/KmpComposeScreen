package com.github.mohammadjoshaghani.composescreen.component.clickableIcon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.DrawableResource

sealed interface IClickableIconModel {
    data class ClickableIconModel(
        val iconId: DrawableResource,
        val title: String? = null,
        val doesButtonHaveBorder: Boolean = true,
        val badgeCount: Int? = null,
        val onIconPressed: () -> Unit,
        val tint: Color? = null,
    ) : IClickableIconModel

    data class ClickableIconVectorModel(
        val iconId: ImageVector,
        val title: String? = null,
        val doesButtonHaveBorder: Boolean = true,
        val badgeCount: Int? = null,
        val onIconPressed: () -> Unit,
        val tint: Color? = null,
    ) : IClickableIconModel

    data object Nothing : IClickableIconModel

}