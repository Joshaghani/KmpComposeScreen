package com.github.mohammadjoshaghani.composescreen.screen.scaffold.compose

import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.component.button.IconButton.ButtonModel
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.BaseScreenScaffold
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.fab.FabIconModel
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.topBar.TopbarModel
import com.github.mohammadjoshaghani.composescreen.utils.AppBarSetting
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListContent(
    topbarModel: TopbarModel = TopbarModel.Nothing,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    appBarSetting: AppBarSetting = AppBarSetting,
    actions: List<ButtonModel> = emptyList(),
    navigationIcon: ButtonModel? = null,
    floatingActionButton: FabIconModel? = null,
    navItems: List<ButtonModel> = emptyList(),
    startPanel: (@Composable () -> Unit)? = null,
    endPanel: (@Composable () -> Unit)? = null,
    bottomBar: (@Composable () -> Unit)? = null,
    isLoading: Boolean = false, // وضعیت لودینگ رو پاس بدید
    onLoadMore: (() -> Unit)? = null, // تابعی که دیتای جدید رو میاره
    content: LazyListScope.() -> Unit
) {
    val listState = rememberLazyListState()

    BaseScreenScaffold(
        topbarModel = topbarModel,
        scrollBehavior = scrollBehavior,
        appBarSetting = appBarSetting,
        actions = actions,
        navigationIcon = navigationIcon,
        floatingActionButton = floatingActionButton,
        navItems = navItems,
        startPanel = startPanel,
        endPanel = endPanel,
        bottomBar = bottomBar,
    ) { padding ->
        Box(modifier = Modifier
            .padding(padding)) {
            LazyColumn(state = listState) {
                // محتوای اصلی لیست
                content()

                item {
                    if (onLoadMore != null) {
                        // منطق تشخیص رسیدن به انتهای لیست
                        val reachEnd by remember {
                            derivedStateOf {
                                val lastVisibleItem =
                                    listState.layoutInfo.visibleItemsInfo.lastOrNull()
                                // اگر آیتم آخر دیده شد و کلاً لیستی وجود داشت
                                lastVisibleItem?.index != 0 && lastVisibleItem?.index == listState.layoutInfo.totalItemsCount - 1
                            }
                        }

                        // صدا زدن تابع لود بیشتر وقتی به آخر لیست رسیدیم
                        LaunchedEffect(reachEnd) {
                            if (reachEnd && !isLoading) {
                                onLoadMore()
                            }
                        }
                    }
                    // نمایش یک Indicator در انتهای لیست هنگام لود شدن
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