package com.github.mohammadjoshaghani.composescreen.commonCompose.clickableIcon

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.extension.clickableTheme
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun ClickableIcon(
    icon: ImageVector,
    badgeCount: Int? = null,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    tint: Color = MaterialTheme.colorScheme.onSurface,
    onClick: () -> Unit,
) {

    when (badgeCount) {
        null -> {
            IconBox(
                icon,
                modifier,
                tint,
                contentDescription,
                onClick
            )
        }

        0 -> {
            BadgedBox(
                badge = { Badge(Modifier.offset(y = 16.dp, x = (-16).dp)) } // فقط دایره خالی
            ) {
                IconBox(
                    icon,
                    modifier,
                    tint,
                    contentDescription,
                    onClick
                )
            }
        }

        else -> {
            BadgedBox(
                badge = {
                    Badge(Modifier.padding(12.dp)) {
                        Text("$badgeCount")
                    }
                }
            ) {
                IconBox(
                    icon,
                    modifier,
                    tint,
                    contentDescription,
                    onClick
                )
            }
        }
    }

}

@Composable
fun ClickableIcon(
    icon: DrawableResource,
    badgeCount: Int? = null,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.onSurface,
    contentDescription: String? = null,
    onClick: () -> Unit,
) {

    when (badgeCount) {
        null -> {
            IconBox(
                icon,
                modifier,
                tint,
                contentDescription,
                onClick
            )
        }

        0 -> {
            BadgedBox(
                badge = { Badge(Modifier.offset(y = 16.dp, x = (-16).dp)) } // فقط دایره خالی
            ) {
                IconBox(
                    icon,
                    modifier,
                    tint,
                    contentDescription,
                    onClick
                )
            }
        }

        else -> {
            BadgedBox(
                badge = {
                    Badge(Modifier.padding(12.dp)) {
                        Text("$badgeCount")
                    }
                }
            ) {
                IconBox(
                    icon,
                    modifier,
                    tint,
                    contentDescription,
                    onClick
                )
            }
        }
    }
}

@Composable
fun IconBox(
    icon: DrawableResource,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.onSurface,
    contentDescription: String? = null,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .size(56.dp)
            .clip(CircleShape)
            .clickableTheme(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = contentDescription,
            modifier = Modifier.size(24.dp),
            tint = tint
        )
    }
}

@Composable
fun IconBox(
    icon: ImageVector,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.onSurface,
    contentDescription: String? = null,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .size(56.dp)
            .clip(CircleShape)
            .clickableTheme(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            modifier = Modifier.size(24.dp),
            tint = tint
        )
    }
}
