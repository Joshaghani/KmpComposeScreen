package com.github.mohammadjoshaghani.composescreen.screen.scaffold.contetn

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnContent(
    footerModel: FooterModel,
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
    content: @Composable ColumnScope.(ScrollState) -> Unit
) {
    val scrollState = rememberScrollState()
    val density = LocalDensity.current

    val overlayHeightPx = with(density) { footerModel.height.toPx() }

    val progress by remember {
        derivedStateOf {
            if (overlayHeightPx <= 0f) return@derivedStateOf 0f

            // کل مقدار قابل اسکرول (انتهای صفحه)
            val maxScroll = scrollState.maxValue.toFloat()
            // مقدار اسکرول شده فعلی
            val currentScroll = scrollState.value.toFloat()

            // چقدر تا انتهای صفحه باقی مانده است؟
            val remainingScroll = maxScroll - currentScroll

            // اگر باقی‌مانده کمتر از ارتفاع اورلی باشد، آن را به نسبت تبدیل می‌کنیم
            // وقتی remainingScroll برابر overlayHeightPx باشد -> progress = 0
            // وقتی remainingScroll برابر 0 باشد -> progress = 1
            if (maxScroll > 0) {
                ((overlayHeightPx - remainingScroll) / overlayHeightPx).coerceIn(0f, 1f)
            } else {
                // اگر محتوا کوتاه باشد و اسکرول نخورد، اورلی را نشان بده
                1f
            }
        }
    }

    // مقدار جابجایی عمودی برای انیمیشن (برعکس کردن پیشرفت)
    val translation = (1f - progress) * overlayHeightPx

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
        translation = translation,
        headerHPx = overlayHeightPx,
        bottomOverlay = if (footerModel.height > 0.dp) {
            {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(footerModel.height)
                        .graphicsLayer {
                            translationY = translation
                        }
                ) {
                    footerModel.content.invoke()
                }
            }
        } else null,
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(scrollState)
        ) {
            content(scrollState)
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