package com.github.mohammadjoshaghani.composescreen.component


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.TopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.github.mohammadjoshaghani.composescreen.utils.AppBarSetting.topAppBar

@Composable
fun UIStickyHeader(
    appBarState: TopAppBarState,
    content: @Composable () -> Unit
) {

    val fraction = appBarState.overlappedFraction

    Surface(
        Modifier
            .fillMaxWidth()
            .zIndex(5f),
        shadowElevation = if (fraction > 0.01f) 8.dp else 0.dp,
        tonalElevation = 1.dp,
        color = if (fraction > 0.01f) topAppBar.scrolledContainerColor else topAppBar.containerColor
    ) {
        content()
    }

}




