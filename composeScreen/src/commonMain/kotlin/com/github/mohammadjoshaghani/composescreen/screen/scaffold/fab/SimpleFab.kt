package com.github.mohammadjoshaghani.composescreen.screen.scaffold.fab

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun SimpleFab(fabIconModel: FabIconModel) {
    FloatingActionButton(
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

