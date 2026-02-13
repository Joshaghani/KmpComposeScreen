package com.github.mohammadjoshaghani.composescreen.screen.scaffold.bottomBar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.component.button.IconButton.ButtonModel
import com.github.mohammadjoshaghani.composescreen.component.button.UIButton
import com.github.mohammadjoshaghani.composescreen.component.image.UIIcon
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.compose.TypeShadow
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.compose.UIShadow
import com.github.mohammadjoshaghani.composescreen.utils.BottomAppBarConfig

@Composable
fun BaseScreenBottomBar(
    bottomBar: (@Composable () -> Unit)? = null,
    bottomConfig: BottomAppBarConfig,
    isWideScreen: Boolean, // فعلاً فقط برای آینده/استایل نگهش می‌داریم
    navItems: List<ButtonModel> = emptyList(),
    maxVisibleItems: Int = 4,
) {
    when {
        bottomBar != null -> {
            BottomBarContainer {
                BottomAppBar(
                    containerColor = bottomConfig.containerColor,
                    contentColor = bottomConfig.contentColor,
                ) {
                    bottomBar()
                }
            }
        }

        !isWideScreen && navItems.isNotEmpty() -> {
            BottomNavBar(
                config = bottomConfig,
                navItems = navItems,
                maxVisibleItems = maxVisibleItems
            )
        }

        else -> Unit
    }
}

@Composable
private fun BottomBarContainer(content: @Composable () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth()) {
        UIShadow(TypeShadow.BOTTOM_BAR)
        content()
    }
}

@Composable
private fun BottomNavBar(
    config: BottomAppBarConfig,
    navItems: List<ButtonModel>,
    maxVisibleItems: Int,
) {
    val mainItems = navItems.take(maxVisibleItems)
    val overflowItems = navItems.drop(maxVisibleItems)

    var overflowExpanded by remember(overflowItems.size) { mutableStateOf(false) }

    BottomBarContainer {
        NavigationBar(
            containerColor = config.containerColor,
            contentColor = config.contentColor,
        ) {
            mainItems.forEach { item ->
                NavigationBarItem(
                    selected = item.isSelected,
                    onClick = item.onClick ?: {},
                    icon = {
                        UIButton(
                            model = item.copy(
                                tint = if (item.isSelected)
                                    MaterialTheme.colorScheme.primary
                                else
                                    MaterialTheme.colorScheme.onSurface,
                                onClick = null
                            )
                        )
                    },
                    label = { item.title?.let { Text(it) } },
                    alwaysShowLabel = item.title != null
                )
            }

            if (overflowItems.isNotEmpty()) {
                NavigationBarItem(
                    selected = false,
                    onClick = { overflowExpanded = true },
                    icon = {
                        Box {
                            Icon(Icons.Default.MoreVert, contentDescription = "More")
                            DropdownMenu(
                                expanded = overflowExpanded,
                                onDismissRequest = { overflowExpanded = false },
                                offset = DpOffset((-16).dp, (-4).dp)
                            ) {
                                overflowItems.forEach { item ->
                                    DropdownMenuItem(
                                        text = { item.title?.let { Text(it) } },
                                        leadingIcon = { UIIcon(item.iconSource) },
                                        onClick = {
                                            overflowExpanded = false
                                            item.onClick?.invoke()
                                        }
                                    )
                                }
                            }
                        }
                    },
                    label = { Text("More") },
                    alwaysShowLabel = false
                )
            }
        }
    }
}