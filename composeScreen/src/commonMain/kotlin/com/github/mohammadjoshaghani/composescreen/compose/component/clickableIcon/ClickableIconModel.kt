package com.github.mohammadjoshaghani.composescreen.compose.component.clickableIcon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig
import org.jetbrains.compose.resources.DrawableResource

sealed interface IClickableIconModel {
    data class ClickableIconModel(
        val iconId: DrawableResource,
        val title: String? = null,
        val doesButtonHaveBorder: Boolean = true,
        val badgeCount: Int? = null,
        val onIconPressed: () -> Unit,
        val tint: Color = ApplicationConfig.config.color.onBackground,
    ) : IClickableIconModel

    data class ClickableIconVectorModel(
        val iconId: ImageVector,
        val title: String? = null,
        val doesButtonHaveBorder: Boolean = true,
        val badgeCount: Int? = null,
        val onIconPressed: () -> Unit,
        val tint: Color = ApplicationConfig.config.color.onBackground,
    ) : IClickableIconModel
}