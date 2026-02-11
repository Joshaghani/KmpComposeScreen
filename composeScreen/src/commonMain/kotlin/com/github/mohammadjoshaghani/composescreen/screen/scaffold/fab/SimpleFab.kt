package com.github.mohammadjoshaghani.composescreen.screen.scaffold.fab

import androidx.compose.foundation.layout.offset
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.bottomBar.NavigationItem
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun SimpleFab(
    fabIconModel: FabIconModel,
    isWideScreen: Boolean,
    navItems: List<NavigationItem> = emptyList(),
) {
    FloatingActionButton(
        modifier = Modifier.offset(
            x =
                if (isWideScreen && navItems.isNotEmpty()) {
                    fabIconModel.paddingFromStart
                } else 0.dp
        ),
        containerColor = MaterialTheme.colorScheme.primary,
        onClick = fabIconModel.onFabPressed
    ) {
        if (fabIconModel.iconResource != null) {
            Icon(
                painterResource(fabIconModel.iconResource),
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

    }
}

