package com.github.mohammadjoshaghani.composescreen.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter.Companion.tint
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.component.UISpacer
import com.github.mohammadjoshaghani.composescreen.component.image.IconSourceType
import com.github.mohammadjoshaghani.composescreen.component.image.UIIcon

@Composable
fun UIBorderButton(
    title: String,
    modifier: Modifier = Modifier,
    leftIcon: IconSourceType? = null,
    rightIcon: IconSourceType? = null,
    enabled: Boolean = true,
    textColor: Color = MaterialTheme.colorScheme.primary,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    shape: Shape = ButtonDefaults.shape,
    border: BorderStroke = BorderStroke(
        1.dp,
        MaterialTheme.colorScheme.primary.copy(alpha = if (enabled) 1f else .5f)
    ),
    onClick: () -> Unit,
) {
    Button(
        enabled = enabled,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.primary,
            containerColor = backgroundColor,
            disabledContainerColor = backgroundColor
        ),
        contentPadding = PaddingValues(vertical = 8.dp),
        border = border,
        modifier = modifier,
        shape = shape,
    ) {
        leftIcon?.let {
            UISpacer(8)
            UIIcon(
                icon = it,
                modifier = Modifier.size(20.dp),
                tint = textColor
            )
            UISpacer(8)
        }
        Text(
            text = title,
            color = if (enabled) {
                textColor
            } else {
                textColor.copy(alpha = .5f)
            },
            softWrap = false,
            maxLines = 1,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .wrapContentWidth()
                .offset(x = if (leftIcon != null) (-10).dp else 0.dp)
        )

        rightIcon?.let {
            UISpacer(8)
            UIIcon(
                icon = it,
                modifier = Modifier
                    .offset(x = (-5).dp)
                    .size(20.dp),
                tint = textColor
            )
            UISpacer(8)
        }
    }
}


