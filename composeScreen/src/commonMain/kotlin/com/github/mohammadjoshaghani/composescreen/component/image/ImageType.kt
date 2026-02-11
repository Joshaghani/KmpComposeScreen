package com.github.mohammadjoshaghani.composescreen.component.image

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.DrawableResource

sealed interface ImageType{
    data class IconVector(val icon: ImageVector) : ImageType
    data class IconResource(val icon: DrawableResource) : ImageType
    data class IconBitmap(val icon: ImageBitmap) : ImageType

}