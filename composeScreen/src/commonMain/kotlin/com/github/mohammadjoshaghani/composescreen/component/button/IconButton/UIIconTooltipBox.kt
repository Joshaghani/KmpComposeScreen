package com.github.mohammadjoshaghani.composescreen.component.button.IconButton

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.component.image.IconSourceType
import com.github.mohammadjoshaghani.composescreen.component.image.UIIcon
import com.github.mohammadjoshaghani.composescreen.extension.themeClickable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IconTooltipBox(
    icon: IconSourceType,
    tooltip: String? = null,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.onSurface,
    onClick: (() -> Unit)? = null,
) {
    TooltipBox(
        positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
        tooltip = {
            tooltip?.let {
                PlainTooltip {
                    Text(tooltip)
                }
            }
        },
        state = rememberTooltipState(),
    ) {
        onClick?.let {
            Box(
                modifier = modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .themeClickable(onClick = it),
                contentAlignment = Alignment.Center
            ) {
                UIIcon(
                    icon = icon,
                    modifier = Modifier.size(24.dp),
                    tint = tint
                )
            }
        } ?: run {
            Box(
                modifier = modifier
                    .size(56.dp)
                    .clip(CircleShape),
                contentAlignment = Alignment.Center
            ) {
                UIIcon(
                    icon = icon,
                    modifier = Modifier.size(24.dp),
                    tint = tint
                )
            }
        }
    }

}
