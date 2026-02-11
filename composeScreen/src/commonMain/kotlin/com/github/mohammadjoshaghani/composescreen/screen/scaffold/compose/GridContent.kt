package com.github.mohammadjoshaghani.composescreen.screen.scaffold.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyGridState
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.component.button.IconButton.IconButtonModel
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.BaseScreenScaffold
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.fab.FabIconModel
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.topBar.TopbarModel
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.topBar.TopbarType
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GridContent(
    topbarModel: TopbarModel = TopbarModel.Nothing,
    topbarType: TopbarType = TopbarType.NORMAL,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    actions: List<IconButtonModel> = emptyList(),
    navigationIcon: IconButtonModel? = null,
    floatingActionButton: FabIconModel? = null,
    navItems: List<IconButtonModel> = emptyList(),
    startPanel: (@Composable () -> Unit)? = null,
    endPanel: (@Composable () -> Unit)? = null,
    bottomBar: (@Composable () -> Unit)? = null,
    columns: GridCells,
    isLoading: Boolean = false, // اضافه شد
    onLoadMore: (() -> Unit)? = null, // اضافه شد
    content: LazyGridScope.(LazyGridState) -> Unit
) {
    val gridState = rememberLazyGridState()


    BaseScreenScaffold(
        topbarModel = topbarModel,
        topbarType = topbarType,
        scrollBehavior = scrollBehavior,
        actions = actions,
        navigationIcon = navigationIcon,
        floatingActionButton = floatingActionButton,
        navItems = navItems,
        startPanel = startPanel,
        endPanel = endPanel,
        bottomBar = bottomBar,
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            LazyVerticalGrid(
                columns = columns,
                state = gridState
            ) {
                content(gridState)

                // تشخیص رسیدن به انتهای گرید
                item(span = { GridItemSpan(maxLineSpan) }) {
                    if (onLoadMore != null) {

                        val reachEnd by remember {
                            derivedStateOf {
                                val lastVisibleItem =
                                    gridState.layoutInfo.visibleItemsInfo.lastOrNull()
                                lastVisibleItem?.index != 0 && lastVisibleItem?.index == gridState.layoutInfo.totalItemsCount - 1
                            }
                        }

                        // اجرای درخواست لود بیشتر
                        LaunchedEffect(reachEnd) {
                            if (reachEnd && !isLoading) {
                                onLoadMore()
                            }
                        }
                    }
                    // نمایش لودینگ به صورت تمام‌عرض (Full Span)
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