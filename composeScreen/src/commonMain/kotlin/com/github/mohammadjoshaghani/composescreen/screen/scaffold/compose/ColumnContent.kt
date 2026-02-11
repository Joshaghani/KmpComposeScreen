package com.github.mohammadjoshaghani.composescreen.screen.scaffold.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.mohammadjoshaghani.composescreen.component.button.IconButton.IconButtonModel
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.BaseScreenScaffold
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.fab.FabIconModel
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.topBar.TopbarModel


@Composable
fun ColumnContent(
    topbarModel: TopbarModel = TopbarModel.Nothing,
    actions: List<IconButtonModel> = emptyList(),
    navigationIcon: IconButtonModel? = null,
    floatingActionButton: FabIconModel? = null,
    navItems: List<IconButtonModel> = emptyList(), // لیست آیتم‌های نویگیشن
    startPanel: (@Composable () -> Unit)? = null,
    endPanel: (@Composable () -> Unit)? = null,
    bottomBar: (@Composable () -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    BaseScreenScaffold(
        topbarModel = topbarModel,
        actions = actions,
        navigationIcon = navigationIcon,
        floatingActionButton = floatingActionButton,
        navItems = navItems,
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