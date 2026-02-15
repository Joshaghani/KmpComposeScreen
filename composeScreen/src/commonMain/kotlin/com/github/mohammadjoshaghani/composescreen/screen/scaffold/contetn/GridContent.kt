package com.github.mohammadjoshaghani.composescreen.screen.scaffold.contetn

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.component.button.IconButton.ButtonModel
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.BaseScreenScaffold
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.fab.FabIconModel
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.footer.FooterModel
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.topBar.TopbarTypeTitle
import com.github.mohammadjoshaghani.composescreen.utils.BottomAppBarConfig
import com.github.mohammadjoshaghani.composescreen.utils.NavigationRailAppBarConfig
import com.github.mohammadjoshaghani.composescreen.utils.TopAppBarConfig
import kotlin.math.min

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GridContent(
    columns: GridCells, // پارامتر جدید برای تعیین تعداد ستون‌ها
    topbarTypeTitle: TopbarTypeTitle = TopbarTypeTitle.Nothing,
    topbarActions: List<ButtonModel> = emptyList(),
    topbarNavigationIcon: ButtonModel? = null,
    topbarSticky: (@Composable () -> Unit)? = null,
    navItems: List<ButtonModel> = emptyList(),
    startPanel: (@Composable () -> Unit)? = null,
    endPanel: (@Composable () -> Unit)? = null,
    fab: FabIconModel? = null,
    bottomBar: (@Composable () -> Unit)? = null,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    isLoading: Boolean = false,
    onLoadMore: (() -> Unit)? = null,
    topAppBarConfig: TopAppBarConfig = TopAppBarConfig(),
    bottomAppBarConfig: BottomAppBarConfig = BottomAppBarConfig(),
    navigationRailAppBarConfig: NavigationRailAppBarConfig = NavigationRailAppBarConfig(),
    content: LazyGridScope.() -> Unit // تغییر اسکوپ به گرید
) {

    // استفاده از State مخصوص گرید
    val gridState = rememberLazyGridState()

    val reachEnd by remember(gridState) {
        derivedStateOf {
            val lastVisibleItem = gridState.layoutInfo.visibleItemsInfo.lastOrNull()
            lastVisibleItem != null &&
                    lastVisibleItem.index == gridState.layoutInfo.totalItemsCount - 1 &&
                    gridState.layoutInfo.totalItemsCount > 0
        }
    }

    var hasTriggered by remember { mutableStateOf(false) }

    LaunchedEffect(reachEnd, isLoading) {
        if (reachEnd && !isLoading && !hasTriggered) {
            hasTriggered = true
            onLoadMore?.invoke()
        } else if (!reachEnd) {
            hasTriggered = false
        }
    }

    BaseScreenScaffold(
        topbarTypeTitle = topbarTypeTitle,
        topbarActions = topbarActions,
        topbarNavigationIcon = topbarNavigationIcon,
        topbarSticky = topbarSticky,
        navItems = navItems,
        startPanel = startPanel,
        endPanel = endPanel,
        fab = fab,
        bottomBar = bottomBar,
        scrollBehavior = scrollBehavior,
        topAppBarConfig = topAppBarConfig,
        bottomAppBarConfig = bottomAppBarConfig,
        navigationRailAppBarConfig = navigationRailAppBarConfig,
    ) { padding ->

        LazyVerticalGrid(
            columns = columns,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            state = gridState,
        ) {
            content()

            if (isLoading) {
                // برای لودینگ باید span را روی maxLineSpan تنظیم کنیم تا کل عرض را بگیرد
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}

