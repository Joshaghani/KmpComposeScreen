package com.github.mohammadjoshaghani.composescreen.bottomSheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig

abstract class BaseSimpleBottomSheet : Screen {

    fun show() {
        ApplicationConfig.bottomSheetNavigator?.show(this)
    }

    @Composable
    override fun Content() {
        Column(
            Modifier
                .background(MaterialTheme.colorScheme.surface)
                .navigationBarsPadding()

        ) {
            ComposeView()
        }
    }

    @Composable
    abstract fun ComposeView()

}