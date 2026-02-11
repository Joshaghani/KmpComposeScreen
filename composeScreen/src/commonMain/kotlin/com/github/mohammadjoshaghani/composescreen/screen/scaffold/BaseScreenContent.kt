package com.github.mohammadjoshaghani.composescreen.screen.scaffold

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.component.UISpacer
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.bottomBar.BadgeItem
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.bottomBar.NavigationItem

@Composable
fun BaseScreenContent(
    navItems: List<NavigationItem> = emptyList(), // لیست آیتم‌های نویگیشن
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
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                NavigationRail {
                    navItems.forEach { item ->
                        NavigationRailItem(
                            selected = item.isSelected,
                            onClick = item.onClick,
                            icon = {
                                IconNav(item)
                            },
                            label = { Text(item.label) }
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


@Composable
private fun IconNav(item: NavigationItem) {
    when (item.badgeItem) {
        BadgeItem.Badge -> BadgedBox(
            badge = {
                Badge(
                    Modifier.offset(
                        y = 16.dp,
                        x = (-16).dp
                    )
                )
            }
        ) {
            Icon(item.icon, contentDescription = item.label)
        }

        is BadgeItem.BadgeWithNumber -> BadgedBox(
            badge = {
                Badge(Modifier.padding(12.dp)) {
                    Text("${item.badgeItem.number}")
                }
            }
        ) {
            Icon(item.icon, contentDescription = item.label)
        }

        BadgeItem.None -> Icon(
            item.icon,
            contentDescription = item.label
        )
    }
}