package com.github.mohammadjoshaghani.composescreen.screen.scaffold.bottomBar

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val icon: ImageVector,
    val label: String,
    val isSelected: Boolean,
    val onClick: () -> Unit
)