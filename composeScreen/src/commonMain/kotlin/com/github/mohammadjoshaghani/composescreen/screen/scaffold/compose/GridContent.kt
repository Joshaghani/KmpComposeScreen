package com.github.mohammadjoshaghani.composescreen.screen.scaffold.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.component.button.IconButton.ButtonModel
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.BaseScreenScaffold
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.fab.FabIconModel
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.topBar.TopbarTypeCompose
import com.github.mohammadjoshaghani.composescreen.utils.AppBarSetting
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GridContent(
    topbarTypeCompose: TopbarTypeCompose = TopbarTypeCompose.Nothing,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    appBarSetting: AppBarSetting = AppBarSetting(),
    actions: List<ButtonModel> = emptyList(),
    navigationIcon: ButtonModel? = null,
    floatingActionButton: FabIconModel? = null,
    navItems: List<ButtonModel> = emptyList(),
    stickyTopbar: (@Composable () -> Unit)? = null,
    startPanel: (@Composable () -> Unit)? = null,
    endPanel: (@Composable () -> Unit)? = null,
    bottomBar: (@Composable () -> Unit)? = null,
    columns: GridCells,
    isLoading: Boolean = false,
    onLoadMore: (() -> Unit)? = null,
    content: LazyGridScope.() -> Unit
) {

    val listState = rememberLazyGridState()
    val reachEnd by remember {
        derivedStateOf {
            val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
            lastVisibleItem != null &&
                    lastVisibleItem.index == listState.layoutInfo.totalItemsCount - 1 &&
                    listState.layoutInfo.totalItemsCount > 0
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
        topbarTypeCompose = topbarTypeCompose,
        scrollBehavior = scrollBehavior,
        appBarSetting = appBarSetting,
        actions = actions,
        navigationIcon = navigationIcon,
        floatingActionButton = floatingActionButton,
        stickyTopbar = stickyTopbar,
        navItems = navItems,
        startPanel = startPanel,
        endPanel = endPanel,
        bottomBar = bottomBar,
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            LazyVerticalGrid(
                columns = columns,
                state = listState
            ) {
                content()

                item(span = { GridItemSpan(maxLineSpan) }) {
                    if (isLoading) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = androidx.compose.ui.Alignment.Center
                        ) {
                            ApplicationConfig.loadingScreen?.let { screen ->
                                screen()
                            } ?: run {
                                CircularProgressIndicator()
                            }
                        }
                    }
                }
            }
        }
    }
}