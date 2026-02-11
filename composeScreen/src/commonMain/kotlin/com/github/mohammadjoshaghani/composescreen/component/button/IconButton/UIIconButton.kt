package com.github.mohammadjoshaghani.composescreen.component.button.IconButton

import androidx.compose.runtime.Composable
import com.github.mohammadjoshaghani.composescreen.component.image.UIBadgeIcon

@Composable
fun UIIconButton(
    model: IconButtonModel
) {

    UIBadgeIcon(model.badgeItem) {
        if (model.title == null) {
            IconTooltipBox(
                model.icon,
                model.tooltip,
                model.modifier,
                model.tint,
                model.onClick,
            )
        } else {
            IconButtonMenu(
                model.title,
                model.doesButtonHaveBorder,
                model.icon,
                model.modifier,
                model.tint,
                model.onClick
            )
        }

    }

}

