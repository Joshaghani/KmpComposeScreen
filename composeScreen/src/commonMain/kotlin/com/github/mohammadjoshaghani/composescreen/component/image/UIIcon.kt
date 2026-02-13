package com.github.mohammadjoshaghani.composescreen.component.image

import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource


@Composable
fun UIIcon(
    icon: IconSourceType?,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current,
    contentDescription: String? = null,
) {
    if (icon == null) return // به جای کرش، هیچی نشون نده

    Icon(
        painter = icon.toPainter(),
        contentDescription = contentDescription,
        modifier = modifier,
        tint = tint
    )
}

@Composable
private fun IconSourceType.toPainter(): Painter = when (this) {
    is IconSourceType.Drawable -> painterResource(drawable)
    is IconSourceType.Icon -> rememberVectorPainter(icon)
    is IconSourceType.Bitmap -> BitmapPainter(bitmap)
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