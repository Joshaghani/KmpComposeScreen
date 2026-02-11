package com.github.mohammadjoshaghani.composescreen.screen.scaffold.bottomBar

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val icon: ImageVector,
    val label: String,
    val isSelected: Boolean,
    val onClick: () -> Unit,
    val badgeItem: BadgeItem = BadgeItem.None
)

sealed interface BadgeItem {
    data object None : BadgeItem
    data object Badge : BadgeItem
    data class BadgeWithNumber(val number: Int) : BadgeItem
}