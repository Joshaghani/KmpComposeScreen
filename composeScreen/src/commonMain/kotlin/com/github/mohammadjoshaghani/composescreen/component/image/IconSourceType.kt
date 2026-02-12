package com.github.mohammadjoshaghani.composescreen.component.image

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.DrawableResource

sealed interface IconSourceType{
    data class Icon(val icon: ImageVector) : IconSourceType
    data class Drawable(val drawable: DrawableResource) : IconSourceType
    data class Bitmap(val bitmap: ImageBitmap) : IconSourceType

}