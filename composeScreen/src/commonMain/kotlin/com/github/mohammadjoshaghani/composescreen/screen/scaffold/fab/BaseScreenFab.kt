package com.github.mohammadjoshaghani.composescreen.screen.scaffold.fab

import androidx.compose.runtime.Composable
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.bottomBar.NavigationItem

@Composable
fun BaseScreenFab(
    fabIconModel: FabIconModel,
    isWideScreen: Boolean,
    navItems: List<NavigationItem>,
) {

    if (fabIconModel.title.isNullOrEmpty()) {
        SimpleFab(fabIconModel, isWideScreen, navItems)
    } else {
        ExtendedFab(fabIconModel, isWideScreen, navItems)
    }

}

