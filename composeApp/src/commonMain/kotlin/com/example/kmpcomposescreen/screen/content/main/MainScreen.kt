package com.example.kmpcomposescreen.screen.content.main

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.example.kmpcomposescreen.screen.content.main.tab.OrdersTab
import com.example.kmpcomposescreen.screen.content.main.utils.getIconsRails
import com.github.mohammadjoshaghani.composescreen.screen.BaseSimpleScreen
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.compose.ColumnContent

class MainScreen : BaseSimpleScreen() {
    @Composable
    override fun ComposeView() {
        TabNavigator(OrdersTab) { tabNavigator ->
            ColumnContent(
                navItems = getIconsRails(tabNavigator)
            ) {
                CurrentTab()
            }
        }
    }
}

