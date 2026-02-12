package com.github.mohammadjoshaghani.composescreen.component


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.TopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.zIndex

@Composable
fun UIFadingHeader(
    appBarState: TopAppBarState,
    height: Dp,
    content: @Composable () -> Unit
) {


    var lastOffset by remember { mutableStateOf(-10000f) }
    var isScrollingDown by remember { mutableStateOf(true) }

    LaunchedEffect(appBarState.contentOffset) {
        val current = appBarState.contentOffset
        isScrollingDown = current > lastOffset
        lastOffset = current
    }

    Box(
        modifier = Modifier
            .zIndex(4f)
            .fillMaxWidth()
            .height(height),
        contentAlignment = Alignment.TopCenter
    ) {
        AnimatedVisibility(
            visible = isScrollingDown,
            enter = slideInVertically(initialOffsetY = { -it }),
            exit = slideOutVertically(targetOffsetY = { -it })
        ) {
            Box(Modifier.fillMaxWidth()) {
                content()
            }
        }
    }
}




