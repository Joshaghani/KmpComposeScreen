package com.github.mohammadjoshaghani.composescreen.screen.scaffold.bottomBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.component.button.IconButton.ButtonModel
import com.github.mohammadjoshaghani.composescreen.component.button.UIButton
import com.github.mohammadjoshaghani.composescreen.component.image.UIIcon
import com.github.mohammadjoshaghani.composescreen.utils.BottomAppBar


@Composable
fun BaseScreenBottomBar(
    bottomBar: (@Composable () -> Unit)? = null,
    bottomAppBar: BottomAppBar,
    isWideScreen: Boolean,
    navItems: List<ButtonModel> = emptyList(), // لیست آیتم‌های نویگیشن
    maxVisibleItems: Int = 4 // حداکثر آیتمی که مستقیم نشون داده میشه

) {

    if (bottomBar != null) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .offset(y = (-5).dp)
                    .fillMaxWidth()
                    .height(5.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Gray.copy(alpha = 0.2f)
                            )
                        )
                    )
            )
            BottomAppBar(
//                modifier = Modifier.navigationBarsPadding(),
                containerColor = bottomAppBar.containerColor,
                contentColor = bottomAppBar.scrolledContainerColor,
            ) {
                bottomBar()
            }
        }
    } else if (!isWideScreen && navItems.isNotEmpty()) {
        var expanded by remember { mutableStateOf(false) }

        // آیتم‌های مستقیم و باقی مانده
        val mainItems = navItems.take(maxVisibleItems)
        val overflowItems = navItems.drop(maxVisibleItems)

        Box(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .offset(y = (-5).dp)
                    .fillMaxWidth()
                    .height(5.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Gray.copy(alpha = 0.2f)
                            )
                        )
                    )
            )

            NavigationBar(
                containerColor = bottomAppBar.containerColor,
                contentColor = bottomAppBar.scrolledContainerColor,
            ) {
                // آیتم‌های اصلی
                mainItems.forEach { item ->
                    NavigationRailItem(
                        selected = item.isSelected,
                        onClick = item.onClick ?: {},
                        icon = {
                            UIButton(
                                model = item.copy(
                                    tint = if (item.isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
                                    onClick = null
                                )
                            )
                        },
                        label = { item.title?.let { Text(it) } },
                        colors = NavigationRailItemDefaults.colors(
                            indicatorColor = Color.Transparent
                        )
                    )
                }

                // اگر آیتم اضافی هست، دکمه Overflow اضافه کن
                if (overflowItems.isNotEmpty()) {
                    NavigationBarItem(
                        selected = false,
                        onClick = { expanded = true },
                        icon = {
                            Box {
                                Icon(Icons.Default.MoreVert, contentDescription = "More")

                                DropdownMenu(
                                    expanded = expanded,
                                    onDismissRequest = { expanded = false },
                                    offset = DpOffset((-16).dp, (-4).dp)
                                ) {
                                    overflowItems.forEach { item ->
                                        DropdownMenuItem(
                                            text = { item.title?.let { Text(it) } },
                                            leadingIcon = { UIIcon(item.iconSource) },
                                            onClick = {
                                                expanded = false
                                                item.onClick?.invoke()
                                            }
                                        )
                                    }
                                }
                            }
                        },
                    )
                }
            }
        }
    }
}