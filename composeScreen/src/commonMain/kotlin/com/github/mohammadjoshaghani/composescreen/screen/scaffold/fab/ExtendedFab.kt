package com.github.mohammadjoshaghani.composescreen.screen.scaffold.fab

import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun ExtendedFab(fabIconModel: FabIconModel) {
    ExtendedFloatingActionButton(
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