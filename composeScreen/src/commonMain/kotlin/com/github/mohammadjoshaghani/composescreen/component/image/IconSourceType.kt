package com.github.mohammadjoshaghani.composescreen.component.image

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.DrawableResource

sealed interface IconSourceType{
    data class IconVector(val icon: ImageVector) : IconSourceType
    data class IconResource(val icon: DrawableResource) : IconSourceType
    data class IconBitmap(val icon: ImageBitmap) : IconSourceType

}