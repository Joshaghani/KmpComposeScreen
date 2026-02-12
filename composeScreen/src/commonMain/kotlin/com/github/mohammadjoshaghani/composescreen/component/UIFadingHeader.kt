package com.github.mohammadjoshaghani.composescreen.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.material3.TopAppBarState
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

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
            .fillMaxWidth()
            .height(height),
        contentAlignment = Alignment.TopCenter
    ) {
        AnimatedVisibility(
            visible = isScrollingDown,
            enter = slideInVertically(initialOffsetY = { -it }) + fadeIn(),
            exit = slideOutVertically(targetOffsetY = { -it }) + fadeOut()
        ) {
            Box(Modifier.fillMaxWidth()) {
                content()
            }
        }
    }
}



