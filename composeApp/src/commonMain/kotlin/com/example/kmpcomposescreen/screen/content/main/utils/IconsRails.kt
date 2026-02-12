package com.example.kmpcomposescreen.screen.content.main.utils


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.example.kmpcomposescreen.screen.content.main.tab.CategoriesTab
import com.example.kmpcomposescreen.screen.content.main.tab.OrdersTab
import com.example.kmpcomposescreen.screen.content.main.tab.ProductsTab
import com.github.mohammadjoshaghani.composescreen.component.button.IconButton.ButtonModel
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig

fun getIconsRails(tabNavigator: TabNavigator): List<ButtonModel> {

    return listOf(

        ButtonModel(
            title = "سفارشات",
            tint = ApplicationConfig.color.onSurfaceVariant,
            icon = Icons.Default.Event,
            isSelected = tabNavigator.current == OrdersTab,
            onClick = {
                tabNavigator.current = OrdersTab
            }),
        ButtonModel(
            title = "دسته بندی",
            tint = ApplicationConfig.color.onSurfaceVariant,
            icon = Icons.Default.Event,
            isSelected = tabNavigator.current == CategoriesTab,
            onClick = {
                tabNavigator.current = CategoriesTab
            }),


        ButtonModel(
            title = "محصولات",
            tint = ApplicationConfig.color.onSurfaceVariant,
            icon = Icons.Default.Event,
            isSelected = tabNavigator.current == ProductsTab,
            onClick = {
                tabNavigator.current = ProductsTab
            }),

        ButtonModel(
            title = "تیکت",
            tint = ApplicationConfig.color.onSurfaceVariant,
            icon = Icons.Default.Event,
            onClick = {
            }),


        ButtonModel(
            title = "نظرات",

            tint = ApplicationConfig.color.onSurfaceVariant,
            icon = Icons.Default.Event,
            onClick = {
            }),


        ButtonModel(
            title = "مشتریان",

            tint = ApplicationConfig.color.onSurfaceVariant,
            icon = Icons.Default.Event,
            onClick = {
            }),


        ButtonModel(
            title = "انواع ارسال",

            tint = ApplicationConfig.color.onSurfaceVariant,
            icon = Icons.Default.Event,
            onClick = {
            }),

        )
}