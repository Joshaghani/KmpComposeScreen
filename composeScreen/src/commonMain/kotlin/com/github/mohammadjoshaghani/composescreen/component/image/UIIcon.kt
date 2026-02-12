package com.github.mohammadjoshaghani.composescreen.component.image

import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource


@Composable
fun UIIcon(
    icon: IconSourceType?,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current,
) {

    when (icon) {
        is IconSourceType.Drawable -> UIIcon(icon.drawable, modifier, tint)
        is IconSourceType.Icon -> UIIcon(icon.icon, modifier, tint)
        is IconSourceType.Bitmap -> UIIcon(icon.bitmap, modifier, tint)
        else -> {
            NullPointerException("icon of UIcon must not be null")
        }
    }

}

@Composable
fun UIIcon(
    drawable: DrawableResource,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current,
) {
    Icon(
        painter = painterResource(drawable),
        contentDescription = null,
        modifier = modifier,
        tint = tint
    )
}

@Composable
fun UIIcon(
    icon: ImageVector,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current,
) {
    Icon(
        imageVector = icon,
        contentDescription = null,
        modifier = modifier,
        tint = tint
    )
}

@Composable
fun UIIcon(
    bitmap: ImageBitmap,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current,
) {
    Icon(
        bitmap = bitmap,
        contentDescription = null,
        modifier = modifier,
        tint = tint
    )
}