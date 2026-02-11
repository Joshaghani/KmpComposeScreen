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
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun UIFadingHeader(
    listState: ScrollState,
    height: Dp,
    content: @Composable () -> Unit) {
    var previousOffset by remember { mutableStateOf(0) }
    var isHeaderVisible by remember { mutableStateOf(true) }

    // اینجا با snapshotFlow خونده میشه، نه مستقیم داخل composition
    LaunchedEffect(listState) {
        snapshotFlow { listState.value }
            .collect { currentOffset ->
                isHeaderVisible = when {
                    currentOffset > previousOffset -> false  // scroll down → hide
                    currentOffset < previousOffset -> true   // scroll up → show
                    else -> isHeaderVisible
                }
                previousOffset = currentOffset
            }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height),
        contentAlignment = Alignment.TopCenter
    ) {
        // placeholder برای جلوگیری از پرش

        AnimatedVisibility(
            visible = isHeaderVisible,
            enter = slideInVertically(initialOffsetY = { -it }) + fadeIn(),
            exit = slideOutVertically(targetOffsetY = { -it }) + fadeOut()
        ) {
            Box(Modifier.fillMaxWidth()) {
                content()
            }
        }
    }
}

@Composable
fun UIFadingHeader(
    listState: LazyListState,
    height: Dp,
    content: @Composable () -> Unit
) {
    var previousOffset by remember { mutableStateOf(0) }
    var isHeaderVisible by remember { mutableStateOf(true) }

    // اینجا با snapshotFlow خونده میشه، نه مستقیم داخل composition
    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemScrollOffset }
            .collect { currentOffset ->
                isHeaderVisible = when {
                    currentOffset > previousOffset -> false  // scroll down → hide
                    currentOffset < previousOffset -> true   // scroll up → show
                    else -> isHeaderVisible
                }
                previousOffset = currentOffset
            }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height),
        contentAlignment = Alignment.TopCenter
    ) {
        // placeholder برای جلوگیری از پرش

        AnimatedVisibility(
            visible = isHeaderVisible,
            enter = slideInVertically(initialOffsetY = { -it }) + fadeIn(),
            exit = slideOutVertically(targetOffsetY = { -it }) + fadeOut()
        ) {
            Box(Modifier.fillMaxWidth()) {
                content()
            }
        }
    }
}

@Composable
fun UIFadingHeader(
    listState: LazyGridState,
    height: Dp,
    content: @Composable () -> Unit
) {
    var previousOffset by remember { mutableStateOf(0) }
    var isHeaderVisible by remember { mutableStateOf(true) }

    // اینجا با snapshotFlow خونده میشه، نه مستقیم داخل composition
    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemScrollOffset }
            .collect { currentOffset ->
                isHeaderVisible = when {
                    currentOffset > previousOffset -> false  // scroll down → hide
                    currentOffset < previousOffset -> true   // scroll up → show
                    else -> isHeaderVisible
                }
                previousOffset = currentOffset
            }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height),
        contentAlignment = Alignment.TopCenter
    ) {
        // placeholder برای جلوگیری از پرش

        AnimatedVisibility(
            visible = isHeaderVisible,
            enter = slideInVertically(initialOffsetY = { -it }) + fadeIn(),
            exit = slideOutVertically(targetOffsetY = { -it }) + fadeOut()
        ) {
            Box(Modifier.fillMaxWidth()) {
                content()
            }
        }
    }
}



