package com.github.mohammadjoshaghani.composescreen.component.image

import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.github.mohammadjoshaghani.composescreen.component.button.IconButton.BadgeItem


@Composable
fun UIBadgeIcon(item: BadgeItem, content: @Composable () -> Unit ) {
    when (item) {
        BadgeItem.Badge -> BadgedBox(
            badge = {
                Badge()
            }
        ) {
            content()
        }

        is BadgeItem.BadgeWithNumber -> BadgedBox(
            badge = {
                Badge() {
                    Text("${item.number}")
                }
            }
        ) {
            content()
        }

        BadgeItem.None -> content()

    }
}


