package com.example.kmpcomposescreen.screen.content.main.utils


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.example.kmpcomposescreen.screen.content.main.tab.CategoriesTab
import com.example.kmpcomposescreen.screen.content.main.tab.OrdersTab
import com.example.kmpcomposescreen.screen.content.main.tab.ProductsTab
import com.github.mohammadjoshaghani.composescreen.component.button.IconButton.IconButtonModel
import com.github.mohammadjoshaghani.composescreen.component.image.IconSourceType
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig
import kmpcomposescreen.composeapp.generated.resources.Res

fun getIconsRails(tabNavigator: TabNavigator): List<IconButtonModel> {

    return listOf(

        IconButtonModel(
            title = "سفارشات",
            tint = ApplicationConfig.color.onSurfaceVariant,
            iconVector = Icons.Default.Event,
            isSelected = tabNavigator.current == OrdersTab,
            onClick = {
                tabNavigator.current = OrdersTab
            }),
        IconButtonModel(
            title = "دسته بندی",
            tint = ApplicationConfig.color.onSurfaceVariant,
            iconVector = Icons.Default.Event,
            isSelected = tabNavigator.current == CategoriesTab,
            onClick = {
                tabNavigator.current = CategoriesTab
            }),


        IconButtonModel(
            title = "محصولات",
            tint = ApplicationConfig.color.onSurfaceVariant,
            iconVector = Icons.Default.Event,
            isSelected = tabNavigator.current == ProductsTab,
            onClick = {
                tabNavigator.current = ProductsTab
            }),

        IconButtonModel(
            title = "تیکت",
            tint = ApplicationConfig.color.onSurfaceVariant,
            iconVector = Icons.Default.Event,
            onClick = {
            }),


        IconButtonModel(
            title = "نظرات",

            tint = ApplicationConfig.color.onSurfaceVariant,
            iconVector = Icons.Default.Event,
            onClick = {
            }),


        IconButtonModel(
            title = "مشتریان",

            tint = ApplicationConfig.color.onSurfaceVariant,
            iconVector = Icons.Default.Event,
            onClick = {
            }),


        IconButtonModel(
            title = "انواع ارسال",

            tint = ApplicationConfig.color.onSurfaceVariant,
            iconVector = Icons.Default.Event,
            onClick = {
            }),

        )
}