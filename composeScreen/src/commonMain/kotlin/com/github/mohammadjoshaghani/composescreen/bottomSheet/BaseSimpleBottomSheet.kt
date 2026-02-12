package com.github.mohammadjoshaghani.composescreen.bottomSheet

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig

abstract class BaseSimpleBottomSheet : Screen {

    fun show() {
        ApplicationConfig.bottomSheetNavigator?.show(this)
    }

    @Composable
    override fun Content() {
        ComposeView()

    }

    @Composable
    abstract fun ComposeView()

}