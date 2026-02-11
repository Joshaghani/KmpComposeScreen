package com.github.mohammadjoshaghani.composescreen.screen.scaffold.fab

import androidx.compose.foundation.layout.offset
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.bottomBar.NavigationItem
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun ExtendedFab(
    fabIconModel: FabIconModel,
    isWideScreen: Boolean,
    navItems: List<NavigationItem> = emptyList(),
) {
    ExtendedFloatingActionButton(
        modifier = Modifier.offset(
            x =
                if (isWideScreen && navItems.isNotEmpty()) {
                    fabIconModel.paddingFromStart
                } else 0.dp
        ),
        containerColor = MaterialTheme.colorScheme.primary,
        onClick = fabIconModel.onFabPressed,
        icon = {
            if (fabIconModel.iconResource != null) {
                Icon(
                    painter = painterResource(fabIconModel.iconResource),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            } else if (fabIconModel.iconVector != null) {
                Icon(
                    fabIconModel.iconVector,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        text = {
            fabIconModel.title?.let {
                Text(
                    text = fabIconModel.title,
                    fontWeight = FontWeight.ExtraBold,
                    color = White
                )
            }
        }
    )
}