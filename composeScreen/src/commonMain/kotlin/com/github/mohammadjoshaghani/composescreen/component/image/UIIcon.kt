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
    icon: IconSourceType,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current,
) {

    when (icon) {
        is IconSourceType.IconResource -> UIIconResource(icon.icon, modifier, tint)
        is IconSourceType.IconVector -> UIIconVector(icon.icon, modifier, tint)
        is IconSourceType.IconBitmap -> UIIconBitmap(icon.icon, modifier, tint)
    }

}

@Composable
fun UIIconResource(
    resource: DrawableResource,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current,
) {
    Icon(
        painter = painterResource(resource),
        contentDescription = null,
        modifier = modifier,
        tint = tint
    )
}

@Composable
fun UIIconVector(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current,
) {
    Icon(
        imageVector = imageVector,
        contentDescription = null,
        modifier = modifier,
        tint = tint
    )
}

@Composable
fun UIIconBitmap(
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