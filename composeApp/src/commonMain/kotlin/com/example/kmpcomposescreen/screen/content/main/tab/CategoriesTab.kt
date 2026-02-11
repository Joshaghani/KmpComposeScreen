package com.example.kmpcomposescreen.screen.content.main.tab

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.example.kmpcomposescreen.screen.content.categories.CategoriesScreen

object CategoriesTab : Tab {
    override val options: TabOptions
        @Composable
        get() = TabOptions(index = 0u, title = "Categories")

    @Composable
    override fun Content() {
        Navigator(CategoriesScreen())
    }
}