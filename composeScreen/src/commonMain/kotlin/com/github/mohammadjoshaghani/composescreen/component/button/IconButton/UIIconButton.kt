@file:OptIn(ExperimentalMaterial3Api::class)

package com.github.mohammadjoshaghani.composescreen.component.button.IconButton

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipAnchorPosition
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.component.image.IconSourceType
import com.github.mohammadjoshaghani.composescreen.component.image.UIBadgeIcon
import com.github.mohammadjoshaghani.composescreen.component.image.UIIcon
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig
import org.jetbrains.compose.resources.DrawableResource


@Composable
fun UIIconButton(
    modifier: Modifier = Modifier,
    tooltip: String? = null,
    badgeItem: BadgeItem = BadgeItem.None,
    shape: Shape = MaterialTheme.shapes.large,
    onClick: (() -> Unit)? = null,
    iconContent: @Composable () -> Unit,
) {
    TooltipBox(
        positionProvider = TooltipDefaults.rememberTooltipPositionProvider(
            TooltipAnchorPosition.Above,
            8.dp
        ),
        tooltip = { tooltip?.let { PlainTooltip { Text(it) } } },
        state = rememberTooltipState(),
        modifier = modifier,
    ) {
        UIBadgeIcon(badgeItem) {
            onClick?.let {
                IconButton(
                    onClick = onClick,
                    shape = shape,
                ) {
                    iconContent()
                }
            } ?: run {
                Box(modifier.padding(16.dp)) {
                    iconContent()
                }
            }
        }
    }
}


@Composable
fun UIIconButton(
    model: IconButtonModel
) {

    when (val icon = model.icon) {
        is IconSourceType.IconBitmap -> UIIconButton(
            icon = icon.icon,
            tooltip = model.tooltip,
            badgeItem = model.badgeItem,
            modifier = model.modifier,
            tint = model.tint,
            shape = model.shape,
            onClick = model.onClick
        )

        is IconSourceType.IconResource -> UIIconButton(
            icon = icon.icon,
            tooltip = model.tooltip,
            badgeItem = model.badgeItem,
            modifier = model.modifier,
            tint = model.tint,
            shape = model.shape,
            onClick = model.onClick
        )

        is IconSourceType.IconVector -> UIIconButton(
            icon = icon.icon,
            tooltip = model.tooltip,
            badgeItem = model.badgeItem,
            modifier = model.modifier,
            tint = model.tint,
            shape = model.shape,
            onClick = model.onClick
        )

        null -> {}
    }

}


@Composable
fun UIIconButton(
    icon: DrawableResource,
    tooltip: String? = null,
    badgeItem: BadgeItem = BadgeItem.None,
    modifier: Modifier = Modifier,
    tint: Color = ApplicationConfig.color.onBackground,
    shape: Shape = MaterialTheme.shapes.large,
    onClick: (() -> Unit)? = null,
) = UIIconButton(
    tooltip = tooltip,
    badgeItem = badgeItem,
    modifier = modifier,
    shape = shape,
    onClick = onClick
) {
    UIIcon(icon, tint = tint)
}

@Composable
fun UIIconButton(
    icon: ImageVector,
    tooltip: String? = null,
    badgeItem: BadgeItem = BadgeItem.None,
    modifier: Modifier = Modifier,
    tint: Color = ApplicationConfig.color.onBackground,
    shape: Shape = MaterialTheme.shapes.large,
    onClick: (() -> Unit)? = null,
) = UIIconButton(
    tooltip = tooltip,
    badgeItem = badgeItem,
    modifier = modifier,
    shape = shape,
    onClick = onClick
) {
    UIIcon(icon, tint = tint)
}

@Composable
fun UIIconButton(
    icon: ImageBitmap,
    tooltip: String? = null,
    badgeItem: BadgeItem = BadgeItem.None,
    modifier: Modifier = Modifier,
    tint: Color = ApplicationConfig.color.onBackground,
    shape: Shape = MaterialTheme.shapes.large,
    onClick: (() -> Unit)? = null,
) = UIIconButton(
    tooltip = tooltip,
    badgeItem = badgeItem,
    modifier = modifier,
    shape = shape,
    onClick = onClick
) {
    UIIcon(icon, tint = tint)
}




