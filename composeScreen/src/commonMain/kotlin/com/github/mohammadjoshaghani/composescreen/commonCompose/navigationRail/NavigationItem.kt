package com.github.mohammadjoshaghani.composescreen.commonCompose.navigationRail

import org.jetbrains.compose.resources.DrawableResource

data class NavigationItem(
    val title: String,
    val unselectedIcon: DrawableResource,
    val selectedIcon: DrawableResource,
    val hasNews: Boolean,
    val badgeCount: Int? = null,
    val onIconClicked: () -> Unit,
)