package com.github.mohammadjoshaghani.composescreen.screen.scaffold.fab

import androidx.compose.runtime.Composable

@Composable
fun BaseScreenFab(fabIconModel: FabIconModel) {

    if (fabIconModel.title.isNullOrEmpty()) {
        SimpleFab(fabIconModel)
    } else {
        ExtendedFab(fabIconModel)
    }

}

