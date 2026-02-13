package com.example.kmpcomposescreen.screen.content.main

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.example.kmpcomposescreen.screen.content.main.tab.CategoriesTab
import com.example.kmpcomposescreen.screen.content.main.utils.getIconsRails
import com.github.mohammadjoshaghani.composescreen.screen.BaseSimpleScreen
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.contetn.ColumnContent

class MainScreen : BaseSimpleScreen() {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun ComposeView() {
        TabNavigator(CategoriesTab) { tabNavigator ->
            ColumnContent(
                navItems = getIconsRails(tabNavigator)
            ) {
                CurrentTab()
            }
        }
    }
}

