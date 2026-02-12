package com.github.mohammadjoshaghani.composescreen.component.button

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.github.mohammadjoshaghani.composescreen.component.button.IconButton.ButtonModel
import com.github.mohammadjoshaghani.composescreen.component.button.IconButton.ButtonType
import com.github.mohammadjoshaghani.composescreen.component.button.IconButton.UIIconButton
import com.github.mohammadjoshaghani.composescreen.component.image.IconSourceType

@Composable
fun UIButton(
    model: ButtonModel
) {
    when (model.buttonType) {
        ButtonType.Icon -> when (val icon = model.iconSource) {
            is IconSourceType.Bitmap -> UIIconButton(
                icon = icon.bitmap,
                tooltip = model.tooltip,
                badgeItem = model.badgeItem,
                modifier = model.modifier,
                tint = model.tint ?: MaterialTheme.colorScheme.primary,
                shape = model.shape,
                onClick = model.onClick
            )

            is IconSourceType.Drawable -> UIIconButton(
                icon = icon.drawable,
                tooltip = model.tooltip,
                badgeItem = model.badgeItem,
                modifier = model.modifier,
                tint = model.tint ?: MaterialTheme.colorScheme.primary,
                shape = model.shape,
                onClick = model.onClick
            )

            is IconSourceType.Icon -> UIIconButton(
                icon = icon.icon,
                tooltip = model.tooltip,
                badgeItem = model.badgeItem,
                modifier = model.modifier,
                tint = model.tint ?: MaterialTheme.colorScheme.primary,
                shape = model.shape,
                onClick = model.onClick
            )

            null -> {}
        }

        ButtonType.BorderButton -> UIBorderButton(
            title = model.title ?: "",
            textColor = model.tint ?: MaterialTheme.colorScheme.primary,
            shape = model.shape,
            modifier = model.modifier,
            leftIcon = model.iconSource,
            onClick = model.onClick ?: {}
        )

        ButtonType.PrimaryButton -> UIPrimaryButton(
            title = model.title ?: "",
            textColor = model.tint ?: MaterialTheme.colorScheme.onPrimary,
            shape = model.shape,
            modifier = model.modifier,
            leftIcon = model.iconSource,
            onClick = model.onClick ?: {}
        )

        ButtonType.TextButton -> UITextButton(
            title = model.title ?: "",
            textColor = model.tint ?: MaterialTheme.colorScheme.primary,
            modifier = model.modifier,
            leftIcon = model.iconSource,
            onClick = model.onClick ?: {}
        )
    }


}

