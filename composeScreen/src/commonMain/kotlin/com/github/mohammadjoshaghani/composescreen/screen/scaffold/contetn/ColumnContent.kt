package com.github.mohammadjoshaghani.composescreen.screen.scaffold.contetn

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.mohammadjoshaghani.composescreen.component.button.IconButton.ButtonModel
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.BaseScreenScaffold
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.fab.FabIconModel
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.topBar.TopbarTypeTitle
import com.github.mohammadjoshaghani.composescreen.utils.BottomAppBarConfig
import com.github.mohammadjoshaghani.composescreen.utils.NavigationRailAppBarConfig
import com.github.mohammadjoshaghani.composescreen.utils.TopAppBarConfig

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
    topAppBarConfig: TopAppBarConfig = TopAppBarConfig(),
    bottomAppBarConfig: BottomAppBarConfig = BottomAppBarConfig(),
    navigationRailAppBarConfig: NavigationRailAppBarConfig = NavigationRailAppBarConfig(),
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(
        rememberTopAppBarState()
    ),
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