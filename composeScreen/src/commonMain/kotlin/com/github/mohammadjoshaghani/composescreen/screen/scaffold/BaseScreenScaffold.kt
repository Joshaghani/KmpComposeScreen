package com.github.mohammadjoshaghani.composescreen.screen.scaffold

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.github.mohammadjoshaghani.composescreen.app.ProvideLayoutDirection
import com.github.mohammadjoshaghani.composescreen.component.button.IconButton.ButtonModel
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.bottomBar.BaseScreenBottomBar
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.fab.BaseScreenFab
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.fab.FabIconModel
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.topBar.BaseScreenTopBar
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.topBar.TopbarTypeTitle
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.compose.TypeShadow
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.compose.UIShadow
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig
import com.github.mohammadjoshaghani.composescreen.utils.BottomAppBarConfig
import com.github.mohammadjoshaghani.composescreen.utils.NavigationRailAppBarConfig
import com.github.mohammadjoshaghani.composescreen.utils.TopAppBarConfig

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun BaseScreenScaffold(
    topbarTypeTitle: TopbarTypeTitle,
    topbarActions: List<ButtonModel>,
    topbarNavigationIcon: ButtonModel?,
    topbarSticky: (@Composable () -> Unit)?,
    navItems: List<ButtonModel>,
    startPanel: (@Composable () -> Unit)?,
    endPanel: (@Composable () -> Unit)?,
    fab: FabIconModel?,
    bottomBar: (@Composable () -> Unit)?,
    topAppBarConfig: TopAppBarConfig = TopAppBarConfig(),
    bottomAppBarConfig: BottomAppBarConfig = BottomAppBarConfig(),
    navigationRailAppBarConfig: NavigationRailAppBarConfig = NavigationRailAppBarConfig(),
    scrollBehavior: TopAppBarScrollBehavior,
    content: @Composable (padding: PaddingValues) -> Unit
) {
    val windowSizeClass = calculateWindowSizeClass()
    val isWideScreen = remember(windowSizeClass) {
        windowSizeClass.widthSizeClass != WindowWidthSizeClass.Compact
    }

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    ProvideLayoutDirection(ApplicationConfig.isRtl) {
        Scaffold(
            modifier = Modifier
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    })
                }
                .imePadding()
                .navigationBarsPadding()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                val fraction =
                    remember { derivedStateOf { scrollBehavior.state.overlappedFraction } }.value
                val containerColor = remember(fraction, topAppBarConfig) {
                    lerp(
                        topAppBarConfig.containerColor,
                        topAppBarConfig.scrolledContainerColor,
                        fraction.coerceIn(0f, 1f)
                    )
                }

                Box(
                    Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Surface(
                        Modifier.zIndex(10000f),
                        tonalElevation = 0.dp,
                        color = containerColor
                    ) {
                        Column {
                            BaseScreenTopBar(
                                scrollBehavior = scrollBehavior,
                                topAppBarConfig = topAppBarConfig,
                                topbarTypeTitle = topbarTypeTitle,
                                actions = topbarActions,
                                navigationIcon = topbarNavigationIcon,
                                topbarSizeType = topAppBarConfig.topbarSizeType
                            )

                            topbarSticky?.invoke()

                        }
                    }

                    if (fraction > 0.01f) UIShadow(TypeShadow.TOP_BAR)

                }
            },
            floatingActionButton = {
                fab?.let { BaseScreenFab(it, isWideScreen, navItems) }
            },
            bottomBar = {
                BaseScreenBottomBar(
                    bottomBar = bottomBar,
                    bottomConfig = bottomAppBarConfig,
                    isWideScreen = isWideScreen,
                    navItems = navItems
                )
            },
            floatingActionButtonPosition = fab?.fabPosition
                ?: FabPosition.End
        ) { padding ->
            Box(Modifier.fillMaxSize()) {
                BaseScreenContent(
                    navItems = navItems,
                    startPanel = startPanel,
                    endPanel = endPanel,
                    paddingValues = padding,
                    isWideScreen = isWideScreen,
                    navigationRailAppBarConfig = navigationRailAppBarConfig,
                    content = content
                )
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun BaseScreenScaffold(
    topbarTypeTitle: TopbarTypeTitle,
    topbarActions: List<ButtonModel>,
    topbarNavigationIcon: ButtonModel?,
    topbarSticky: (@Composable () -> Unit)?,
    navItems: List<ButtonModel>,
    fab: FabIconModel?,
    bottomBar: (@Composable () -> Unit)?,
    topAppBarConfig: TopAppBarConfig = TopAppBarConfig(),
    bottomAppBarConfig: BottomAppBarConfig = BottomAppBarConfig(),
    scrollBehavior: TopAppBarScrollBehavior,
    content: @Composable (padding: PaddingValues) -> Unit
) {
    val windowSizeClass = calculateWindowSizeClass()
    val isWideScreen = remember(windowSizeClass) {
        windowSizeClass.widthSizeClass != WindowWidthSizeClass.Compact
    }

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    ProvideLayoutDirection(ApplicationConfig.isRtl) {
        Scaffold(
            modifier = Modifier
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    })
                }
                .imePadding()
                .navigationBarsPadding()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                val fraction =
                    remember { derivedStateOf { scrollBehavior.state.overlappedFraction } }.value
                val containerColor = remember(fraction, topAppBarConfig) {
                    lerp(
                        topAppBarConfig.containerColor,
                        topAppBarConfig.scrolledContainerColor,
                        fraction.coerceIn(0f, 1f)
                    )
                }

                Box(
                    Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Surface(
                        Modifier.zIndex(10000f),
                        tonalElevation = 0.dp,
                        color = containerColor
                    ) {
                        Column {
                            BaseScreenTopBar(
                                scrollBehavior = scrollBehavior,
                                topAppBarConfig = topAppBarConfig,
                                topbarTypeTitle = topbarTypeTitle,
                                actions = topbarActions,
                                navigationIcon = topbarNavigationIcon,
                                topbarSizeType = topAppBarConfig.topbarSizeType
                            )

                            topbarSticky?.invoke()

                        }
                    }

                    if (fraction > 0.01f) UIShadow(TypeShadow.TOP_BAR)

                }
            },
            floatingActionButton = {
                fab?.let { BaseScreenFab(it, isWideScreen, navItems) }
            },
            bottomBar = {
                BaseScreenBottomBar(
                    bottomBar = bottomBar,
                    bottomConfig = bottomAppBarConfig,
                    isWideScreen = isWideScreen,
                    navItems = navItems
                )
            },
            floatingActionButtonPosition = fab?.fabPosition
                ?: FabPosition.End
        ) { padding ->
            content(padding)
        }
    }
}