package com.github.mohammadjoshaghani.composescreen.screen.scaffold.fab

import androidx.compose.runtime.Composable
import com.github.mohammadjoshaghani.composescreen.component.button.IconButton.IconButtonModel

@Composable
fun BaseScreenFab(
    fabIconModel: FabIconModel,
    isWideScreen: Boolean,
    navItems: List<IconButtonModel>,
) {

    if (fabIconModel.customCompose != null) {
        fabIconModel.customCompose()
    } else if (fabIconModel.title.isNullOrEmpty()) {
        SimpleFab(fabIconModel, isWideScreen, navItems)
    } else {
        ExtendedFab(fabIconModel, isWideScreen, navItems)
    }

}

