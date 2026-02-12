package com.github.mohammadjoshaghani.composescreen.component.button

import androidx.compose.foundation.basicMarquee
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
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.component.UISpacer
import com.github.mohammadjoshaghani.composescreen.component.image.IconSourceType
import com.github.mohammadjoshaghani.composescreen.component.image.UIIcon

@Composable
fun UIPrimaryButton(
    title: String,
    modifier: Modifier = Modifier,
    leftIcon: IconSourceType? = null,
    rightIcon: IconSourceType? = null,
    enabled: Boolean = true,
    textColor: Color = White,
    color: Color = MaterialTheme.colorScheme.primary,
    shape: Shape = MaterialTheme.shapes.large,
    onClick: () -> Unit,
) {
    Button(
        enabled = enabled,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            contentColor = color,
            containerColor = color,
            disabledContainerColor = color
        ),
        contentPadding = PaddingValues(vertical = 8.dp),
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

