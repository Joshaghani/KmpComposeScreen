package com.github.mohammadjoshaghani.composescreen.component.image

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.component.button.IconButton.BadgeItem


@Composable
fun UIBadgeIcon(item: BadgeItem, content: @Composable () -> Unit ) {
    when (item) {
        BadgeItem.Badge -> BadgedBox(
            badge = {
                Badge(
                    Modifier.offset(
                        y = 16.dp,
                        x = (-16).dp
                    )
                )
            }
        ) {
            content()
        }

        is BadgeItem.BadgeWithNumber -> BadgedBox(
            badge = {
                Badge(Modifier.padding(12.dp)) {
                    Text("${item.number}")
                }
            }
        ) {
            content()
        }

        BadgeItem.None -> content()

    }
}


