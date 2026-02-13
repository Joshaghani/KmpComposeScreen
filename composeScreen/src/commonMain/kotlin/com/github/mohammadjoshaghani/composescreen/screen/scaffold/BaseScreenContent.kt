package com.github.mohammadjoshaghani.composescreen.screen.scaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.component.button.IconButton.ButtonModel
import com.github.mohammadjoshaghani.composescreen.component.button.UIButton
import com.github.mohammadjoshaghani.composescreen.utils.NavigationRailAppBarConfig

@Composable
fun BaseScreenContent(
    navItems: List<ButtonModel> = emptyList(), // لیست آیتم‌های نویگیشن
    startPanel: (@Composable () -> Unit)? = null,
    endPanel: (@Composable () -> Unit)? = null,
    paddingValues: PaddingValues,
    isWideScreen: Boolean,
    navigationRailAppBarConfig: NavigationRailAppBarConfig,
    content: @Composable (PaddingValues) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues) // پدینگ Scaffold را اعمال می‌کنیم
    ) {
        // ۱. اگر صفحه عریض بود، NavigationRail را سمت چپ (یا راست در فارسی) نشان بده
        if (isWideScreen && navItems.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(color = navigationRailAppBarConfig.backGroundColor)
                    .clip(navigationRailAppBarConfig.shape)
                    .verticalScroll(rememberScrollState())
            ) {

                Surface(
                    modifier = Modifier
                        .padding(navigationRailAppBarConfig.padding)
                        .fillMaxHeight(),
                    shadowElevation = navigationRailAppBarConfig.shadowElevation,
                    color = navigationRailAppBarConfig.color,
                    shape = navigationRailAppBarConfig.shape,
                    tonalElevation = navigationRailAppBarConfig.tonalElevation

                ) {
                    NavigationRail(
                        containerColor = navigationRailAppBarConfig.color,
                    ) {
                        navItems.forEach { item ->
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
                                    indicatorColor = Color.Transparent // 👈 حذف دایره انتخاب
                                )
                            )
                        }
                    }
                }
            }
        }

        // ۲. پنل شروع (اختیاری)
        if (isWideScreen && startPanel != null) {
            Box(modifier = Modifier.weight(1f)) { startPanel() }
        }

        // ۳. محتوای اصلی (همیشه هست)
        Box(modifier = Modifier.weight(2f)) {
            // پدینگ داخلی را صفر می‌دهیم چون والد هندل کرده
            content(PaddingValues(0.dp))
        }

        // ۴. پنل پایان (اختیاری)
        if (isWideScreen && endPanel != null) {
            Box(modifier = Modifier.weight(1f)) { endPanel() }
        }
    }
}

