package com.github.mohammadjoshaghani.composescreen.screen.scaffold.compose

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.github.mohammadjoshaghani.composescreen.component.button.IconButton.IconButtonModel
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.BaseScreenScaffold
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.fab.FabIconModel
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.topBar.TopbarModel


// این متد را در اسکرین‌های خود صدا بزنید اگر محتوای ثابت دارید
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
    content: @Composable ColumnScope.(ScrollState) -> Unit
) {

    val scrollState = rememberScrollState()
    // منطق تشخیص اسکرول برای Column
    val isScrolled by remember { derivedStateOf { scrollState.value > 0 } }

    BaseScreenScaffold(
        isScrolled = isScrolled, // محتوای ثابت اسکرول نمی‌شود
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
            content(scrollState)
        }
    }
}