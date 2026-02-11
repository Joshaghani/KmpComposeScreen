package com.github.mohammadjoshaghani.composescreen.component.button.IconButton

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.github.mohammadjoshaghani.composescreen.component.button.UIBorderButton
import com.github.mohammadjoshaghani.composescreen.component.button.UITextButton
import com.github.mohammadjoshaghani.composescreen.component.image.IconSourceType

@Composable
fun IconButtonMenu(
    title: String,
    doesButtonHaveBorder: Boolean = true,
    icon: IconSourceType,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.onSurface,
    onClick: () -> Unit,
) {

    if (doesButtonHaveBorder) {
        UIBorderButton(
            title = title,
            modifier = modifier,
            leftIcon = icon,
            onClick = onClick,
            textColor = tint
        )
    } else {
        UITextButton(
            title = title,
            modifier = modifier,
            leftIcon = icon,
            clickable = onClick,
            textColor = tint,
        )
    }

}