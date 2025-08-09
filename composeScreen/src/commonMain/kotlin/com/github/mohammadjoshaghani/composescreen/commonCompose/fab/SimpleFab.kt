package com.github.mohammadjoshaghani.composescreen.commonCompose.fab

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.White
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun SimpleFab(iconId: DrawableResource, onClick: () -> Unit) {
    FloatingActionButton(
        containerColor = MaterialTheme.colorScheme.primary,
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(iconId),
            contentDescription = null,
            tint = White
        )
    }
}

