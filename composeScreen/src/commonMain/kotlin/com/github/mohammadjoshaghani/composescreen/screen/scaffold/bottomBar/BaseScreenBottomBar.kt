package com.github.mohammadjoshaghani.composescreen.screen.scaffold.bottomBar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun BaseScreenBottomBar(
    bottomBar: (@Composable () -> Unit)? = null,
    isWideScreen: Boolean,
    navItems: List<NavigationItem> = emptyList(), // لیست آیتم‌های نویگیشن
) {
    if (bottomBar != null) {
        bottomBar()
    } else if (!isWideScreen && navItems.isNotEmpty()) {
        NavigationBar {
            navItems.forEach { item ->
                NavigationBarItem(
                    selected = item.isSelected,
                    onClick = item.onClick,
                    icon = { Icon(item.icon, contentDescription = item.label) },
                    label = { Text(item.label) }
                )
            }
        }
    }
}