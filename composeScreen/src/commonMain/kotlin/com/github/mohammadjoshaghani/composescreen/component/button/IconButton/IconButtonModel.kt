package com.github.mohammadjoshaghani.composescreen.component.button.IconButton

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.github.mohammadjoshaghani.composescreen.component.image.ImageType
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig

data class IconButtonModel(
    val icon: ImageType,
    val title: String? = null,
    val tooltip: String? = null,
    val doesButtonHaveBorder: Boolean = true,
    val isSelected: Boolean = false,
    val badgeItem: BadgeItem = BadgeItem.None,
    val modifier: Modifier = Modifier,
    val tint: Color = ApplicationConfig.config.color.onBackground,
    val onClick: () -> Unit,
)