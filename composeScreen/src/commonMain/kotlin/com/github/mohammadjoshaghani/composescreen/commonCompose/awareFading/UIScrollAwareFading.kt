package com.github.mohammadjoshaghani.composescreen.commonCompose.awareFading

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.BaseScreenLazyList
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.compsoe.stickyHeaderHeight

var awareHeaderHeight by mutableStateOf(0.dp)
var showAwareHeader by mutableStateOf(true)

@Composable
fun UIScrollAwareFading(
    screen: BaseScreenLazyList<*, *, *, *>,
    isSticky: Boolean,
    modifier: Modifier = Modifier,
    contentItemRows: @Composable () -> Unit,
    stickyContent: @Composable () -> Unit,
    headerContent: @Composable (Modifier) -> Unit,
) {
    val density = LocalDensity.current

    screen.viewModel.launchOnScope {
        screen.scrollEvents.collect {
            showAwareHeader = it
        }
    }

    Box(modifier) {

        contentItemRows()

        AnimatedVisibility(
            visible = showAwareHeader,
            enter = slideInVertically(initialOffsetY = { -it }) + fadeIn(),
            exit = slideOutVertically(targetOffsetY = { -it }) + fadeOut()
        ) {
            Column {
                if (isSticky)
                    Spacer(modifier = Modifier.Companion.height(stickyHeaderHeight))
                headerContent(
                    Modifier
                        .onGloballyPositioned {
                            awareHeaderHeight = with(density) { it.size.height.toDp() }
                        }
                )
            }
        }

        if (isSticky) {
            stickyContent()
        } else stickyHeaderHeight = 0.dp

    }
}