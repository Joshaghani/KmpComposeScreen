package com.github.mohammadjoshaghani.composescreen.screen.scaffold.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.mohammadjoshaghani.composescreen.component.button.IconButton.ButtonModel
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.BaseScreenScaffold
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.fab.FabIconModel
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.topBar.TopbarTypeCompose
import com.github.mohammadjoshaghani.composescreen.utils.AppBarSetting


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnContent(
    topbarTypeCompose: TopbarTypeCompose = TopbarTypeCompose.Nothing,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    appBarSetting: AppBarSetting = AppBarSetting(),
    actions: List<ButtonModel> = emptyList(),
    navigationIcon: ButtonModel? = null,
    floatingActionButton: FabIconModel? = null,
    navItems: List<ButtonModel> = emptyList(), // لیست آیتم‌های نویگیشن
    stickyTopbar: (@Composable () -> Unit)? = null,
    startPanel: (@Composable () -> Unit)? = null,
    endPanel: (@Composable () -> Unit)? = null,
    bottomBar: (@Composable () -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    BaseScreenScaffold(
        topbarTypeCompose = topbarTypeCompose,
        scrollBehavior = scrollBehavior,
        appBarSetting = appBarSetting,
        actions = actions,
        navigationIcon = navigationIcon,
        floatingActionButton = floatingActionButton,
        navItems = navItems,
        stickyTopbar = stickyTopbar,
        startPanel = startPanel,
        endPanel = endPanel,
        bottomBar = bottomBar,
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