package com.github.mohammadjoshaghani.composescreen.screen.scaffold.bottomBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp


@Composable
fun BaseScreenBottomBar(
    bottomBar: (@Composable () -> Unit)? = null,
    isWideScreen: Boolean,
    navItems: List<NavigationItem> = emptyList(), // لیست آیتم‌های نویگیشن
    maxVisibleItems: Int = 4 // حداکثر آیتمی که مستقیم نشون داده میشه

) {
    if (bottomBar != null) {
        bottomBar()
    } else if (!isWideScreen && navItems.isNotEmpty()) {
        var expanded by remember { mutableStateOf(false) }

        // آیتم‌های مستقیم و باقی مانده
        val mainItems = navItems.take(maxVisibleItems)
        val overflowItems = navItems.drop(maxVisibleItems)

        NavigationBar {
            // آیتم‌های اصلی
            mainItems.forEach { item ->
                NavigationBarItem(
                    selected = item.isSelected,
                    onClick = item.onClick,
                    icon = { Icon(item.icon, contentDescription = item.label) },
                    label = { Text(item.label) }
                )
            }

            // اگر آیتم اضافی هست، دکمه Overflow اضافه کن
            if (overflowItems.isNotEmpty()) {
                NavigationBarItem(
                    selected = false,
                    onClick = { expanded = true },
                    icon = { Icon(Icons.Default.MoreVert, contentDescription = "More") },
                    label = { Text("More") }
                )

                // منوی کشویی
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    offset = DpOffset(5000.dp, 0.dp)
                ) {
                    overflowItems.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(item.label) },
                            onClick = {
                                expanded = false
                                item.onClick()
                            }
                        )
                    }
                }
            }
        }
    }
}