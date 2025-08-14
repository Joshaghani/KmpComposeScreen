package com.github.mohammadjoshaghani.composescreen.commonCompose.navigationRail

import androidx.compose.ui.graphics.Color
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig
import org.jetbrains.compose.resources.DrawableResource

data class NavigationItem(
    val title: String,
    val unselectedIcon: DrawableResource,
    val unselectedColor: Color = ApplicationConfig.config.color.surfaceVariant,
    val selectedColor: Color = ApplicationConfig.config.color.onSurfaceVariant,
    val selectedIcon: DrawableResource,
    val hasNews: Boolean,
    val badgeCount: Int? = null,
    val onIconClicked: () -> Unit,
)