package com.example.kmpcomposescreen.screen.content.main.tab

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.example.kmpcomposescreen.screen.content.order.OrderScreen
import com.example.kmpcomposescreen.screen.content.products.ProductsScreen

object ProductsTab : Tab {
    override val options: TabOptions
        @Composable
        get() = TabOptions(index = 2u, title = "Products")

    @Composable
    override fun Content() {
        Navigator(ProductsScreen())
    }
}