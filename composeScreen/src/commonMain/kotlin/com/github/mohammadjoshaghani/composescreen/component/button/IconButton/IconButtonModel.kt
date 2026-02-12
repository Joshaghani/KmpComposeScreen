package com.github.mohammadjoshaghani.composescreen.component.button.IconButton

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.component.image.IconSourceType
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig
import org.jetbrains.compose.resources.DrawableResource

data class IconButtonModel(

    val iconVector: ImageVector? = null,
    val iconResource: DrawableResource? = null,
    val iconBitmap: ImageBitmap? = null,

    val title: String? = null,
    val tooltip: String? = null,
    val doesButtonHaveBorder: Boolean = true,
    val isSelected: Boolean = false,
    val badgeItem: BadgeItem = BadgeItem.None,
    val modifier: Modifier = Modifier,
    val shape: Shape = RoundedCornerShape(12.dp),
    val tint: Color = ApplicationConfig.color.onBackground,
    val onClick: (() -> Unit)? = null,
) {

    val icon: IconSourceType?
        get() {
            if (iconVector != null) {
                return IconSourceType.IconVector(iconVector)
            } else if (iconResource != null) {
                return IconSourceType.IconResource(iconResource)
            } else if (iconBitmap != null) {
                return IconSourceType.IconBitmap(iconBitmap)
            }
            return null
        }
}