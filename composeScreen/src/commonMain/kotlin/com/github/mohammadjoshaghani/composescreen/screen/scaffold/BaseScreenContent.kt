package com.github.mohammadjoshaghani.composescreen.screen.scaffold

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.component.image.UIBadgeIcon
import com.github.mohammadjoshaghani.composescreen.component.button.IconButton.IconButtonModel
import com.github.mohammadjoshaghani.composescreen.component.button.IconButton.IconTooltipBox

@Composable
fun BaseScreenContent(
    navItems: List<IconButtonModel> = emptyList(), // لیست آیتم‌های نویگیشن
    startPanel: (@Composable () -> Unit)? = null,
    endPanel: (@Composable () -> Unit)? = null,
    paddingValues: PaddingValues,
    isWideScreen: Boolean,
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
                    .border(1.dp, color = MaterialTheme.colorScheme.onSecondary)
                    .verticalScroll(rememberScrollState())
            ) {
                NavigationRail {
                    navItems.forEach { item ->
                        NavigationRailItem(
                            selected = item.isSelected,
                            onClick = item.onClick,
                            icon = {
                                UIBadgeIcon(item.badgeItem) {
                                    IconTooltipBox(
                                        icon = item.icon,
                                    )
                                }
                            },
                            label = { item.title?.let { Text(it) } }
                        )
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

