package com.github.mohammadjoshaghani.composescreen.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig

@Composable
fun ProvideLayoutDirection(isRtl: Boolean, content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalLayoutDirection provides
                if (isRtl) LayoutDirection.Rtl else LayoutDirection.Ltr
    ) {
        content()
    }
}