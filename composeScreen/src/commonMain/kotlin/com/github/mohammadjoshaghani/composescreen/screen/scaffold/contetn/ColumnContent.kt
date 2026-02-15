package com.github.mohammadjoshaghani.composescreen.screen.scaffold.contetn

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.github.mohammadjoshaghani.composescreen.component.button.IconButton.ButtonModel
import com.github.mohammadjoshaghani.composescreen.component.button.UIButton
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.BaseScreenContent
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.BaseScreenScaffold
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.fab.FabIconModel
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.topBar.TopbarTypeTitle
import com.github.mohammadjoshaghani.composescreen.utils.BottomAppBarConfig
import com.github.mohammadjoshaghani.composescreen.utils.NavigationRailAppBarConfig
import com.github.mohammadjoshaghani.composescreen.utils.TopAppBarConfig

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun ColumnContent(
    topbarTypeTitle: TopbarTypeTitle = TopbarTypeTitle.Nothing,
    topbarActions: List<ButtonModel> = emptyList(),
    topbarNavigationIcon: ButtonModel? = null,
    topbarSticky: (@Composable () -> Unit)? = null,
    navItems: List<ButtonModel> = emptyList(),
    startPanel: (@Composable () -> Unit)? = null,
    endPanel: (@Composable () -> Unit)? = null,
    footerPanel: (@Composable () -> Unit),
    fab: FabIconModel? = null,
    bottomBar: (@Composable () -> Unit)? = null,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    topAppBarConfig: TopAppBarConfig = TopAppBarConfig(),
    bottomAppBarConfig: BottomAppBarConfig = BottomAppBarConfig(),
    navigationRailAppBarConfig: NavigationRailAppBarConfig = NavigationRailAppBarConfig(),
    content: @Composable ColumnScope.() -> Unit
) {
    val scrollState = rememberScrollState()

    val windowSizeClass = calculateWindowSizeClass()
    val isWideScreen = remember(windowSizeClass) {
        windowSizeClass.widthSizeClass != WindowWidthSizeClass.Compact
    }

    BaseScreenScaffold(
        topbarTypeTitle = topbarTypeTitle,
        topbarActions = topbarActions,
        topbarNavigationIcon = topbarNavigationIcon,
        topbarSticky = topbarSticky,
        navItems = navItems,
        fab = fab,
        bottomBar = bottomBar,
        topAppBarConfig = topAppBarConfig,
        bottomAppBarConfig = bottomAppBarConfig,
        scrollBehavior = scrollBehavior,
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(scrollState)
        ) {
            BaseScreenContent(
                navItems = navItems,
                startPanel = startPanel,
                endPanel = endPanel,
                paddingValues = PaddingValues(0.dp),
                isWideScreen = isWideScreen,
                navigationRailAppBarConfig = navigationRailAppBarConfig,
            ) {
                Column {
                    content()
                }
            }

            footerPanel.let {
                Box(
                    modifier = Modifier
                        .padding(bottom = padding.calculateBottomPadding())
                        .zIndex(999f)
                ) {
                    it()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnContent(
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
    topAppBarConfig: TopAppBarConfig = TopAppBarConfig(),
    bottomAppBarConfig: BottomAppBarConfig = BottomAppBarConfig(),
    navigationRailAppBarConfig: NavigationRailAppBarConfig = NavigationRailAppBarConfig(),
    content: @Composable ColumnScope.() -> Unit
) {
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            content()
        }
    }
}