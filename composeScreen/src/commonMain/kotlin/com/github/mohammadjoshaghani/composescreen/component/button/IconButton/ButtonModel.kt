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

data class ButtonModel(
    val buttonType: ButtonType = ButtonType.Icon,
    val icon: ImageVector? = null,
    val drawable: DrawableResource? = null,
    val bitmap: ImageBitmap? = null,
    val title: String? = null,
    val tooltip: String? = null,
    val isSelected: Boolean = false,
    val badgeItem: BadgeItem = BadgeItem.None,
    val modifier: Modifier = Modifier,
    val shape: Shape = RoundedCornerShape(12.dp),
    val tint: Color? = null,
    val onClick: (() -> Unit)? = null,
) {

    val iconSource: IconSourceType?
        get() {
            if (icon != null) {
                return IconSourceType.Icon(icon)
            } else if (drawable != null) {
                return IconSourceType.Drawable(drawable)
            } else if (bitmap != null) {
                return IconSourceType.Bitmap(bitmap)
            }
            return null
        }
}

enum class ButtonType {
    Icon,
    BorderButton,
    PrimaryButton,
    TextButton,
}