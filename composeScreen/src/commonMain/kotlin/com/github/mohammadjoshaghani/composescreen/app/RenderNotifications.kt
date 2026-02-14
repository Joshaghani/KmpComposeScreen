package com.github.mohammadjoshaghani.composescreen.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.window.Popup
import com.github.mohammadjoshaghani.composescreen.screen.toast.UIToastNotification
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig


@Composable
fun RenderNotifications() {
    ProvideLayoutDirection(ApplicationConfig.isRtl) {
        Popup(
            alignment = Alignment.TopCenter,
            offset = IntOffset(x = -Int.MAX_VALUE, y = 0)
        ) {
            UIToastNotification()
        }
    }
}
