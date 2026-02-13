package com.github.mohammadjoshaghani.composescreen.screen.scaffold.contetn

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
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
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.topBar.TopbarTypeTitle
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig
import com.github.mohammadjoshaghani.composescreen.utils.BottomAppBarConfig
import com.github.mohammadjoshaghani.composescreen.utils.NavigationRailAppBarConfig
import com.github.mohammadjoshaghani.composescreen.utils.TopAppBarConfig


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListContent(
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
    content: LazyListScope.() -> Unit
) {
    val listState = rememberLazyListState()
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            LazyColumn(state = listState) {
                content()

                if (isLoading) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            ApplicationConfig.loadingScreen?.invoke()
                                ?: CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}
