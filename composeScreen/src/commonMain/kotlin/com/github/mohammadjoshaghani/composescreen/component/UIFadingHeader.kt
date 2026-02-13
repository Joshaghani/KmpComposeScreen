package com.github.mohammadjoshaghani.composescreen.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.material3.TopAppBarState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.zIndex

/**
 * منطق مشترک برای انیمیشن و وضعیت اسکرول
 */
@Composable
fun UIFadingHeader(
    appBarState: TopAppBarState,
    height: Dp,
    content: @Composable () -> Unit
) {
    var lastOffset by remember { mutableFloatStateOf(appBarState.contentOffset) }
    var isScrollingDown by remember { mutableStateOf(true) }

    LaunchedEffect(appBarState.contentOffset) {
        val current = appBarState.contentOffset
        val delta = current - lastOffset

        // فقط وقتی اختلاف قابل توجهه جهت رو تغییر بده
        if (kotlin.math.abs(delta) > 2f) {
            isScrollingDown = delta > 0
            lastOffset = current
        }
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


// --- استفاده در Scope های مختلف ---

@OptIn(ExperimentalFoundationApi::class)
fun LazyListScope.itemFadingHeader(
    appBarState: TopAppBarState,
    height: Dp,
    content: @Composable () -> Unit
) {
    stickyHeader {
        UIFadingHeader(appBarState, height, content)
    }
}

@OptIn(ExperimentalFoundationApi::class)
fun LazyGridScope.itemFadingHeader(
    appBarState: TopAppBarState,
    height: Dp,
    content: @Composable () -> Unit
) {
    stickyHeader {
        UIFadingHeader(appBarState, height, content)
    }
}

