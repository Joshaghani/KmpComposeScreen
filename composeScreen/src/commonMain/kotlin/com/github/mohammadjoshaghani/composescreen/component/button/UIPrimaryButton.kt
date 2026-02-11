package com.github.mohammadjoshaghani.composescreen.component.button

import androidx.compose.foundation.basicMarquee
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
import com.github.mohammadjoshaghani.composescreen.component.image.ImageType
import com.github.mohammadjoshaghani.composescreen.component.image.UIIcon

@Composable
fun UIPrimaryButton(
    title: String,
    modifier: Modifier = Modifier,
    leftIconPainter: ImageType? = null,
    rightIconPainter: ImageType? = null,
    enabled: Boolean = true,
    paddingTop: Dp = 0.dp,
    textColor: Color = White,
    color: Color = MaterialTheme.colorScheme.primary,
    tint: Color = MaterialTheme.colorScheme.onPrimary,
    shape: Shape = MaterialTheme.shapes.large,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            color,
            contentColor = color,
        ),
        modifier = modifier.padding(top = paddingTop),
        shape = shape
    ) {
        leftIconPainter?.let {
            UIIcon(
                icon = it,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .offset(x = (-10).dp)
                    .size(20.dp),
                tint = tint
            )
        }

        Text(
            text = title,
            color = textColor,
            softWrap = false,
            maxLines = 1,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .wrapContentWidth()
                .offset(x = if (leftIconPainter != null) (-10).dp else 0.dp)
                .basicMarquee()
        )

        rightIconPainter?.let {
            UIIcon(
                icon = it,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .offset(x = (-5).dp)
                    .size(20.dp),
                tint = tint
            )
        }
    }
}

