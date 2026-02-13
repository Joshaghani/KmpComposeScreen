package com.github.mohammadjoshaghani.composescreen.screen.scaffold

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
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
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.topBar.TopbarTypeCompose
import com.github.mohammadjoshaghani.composescreen.utils.AppBarSetting
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.input.pointer.pointerInput
import com.github.mohammadjoshaghani.composescreen.component.UISpacer
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.topBar.TypeShadow
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.topBar.UIShadow


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun BaseScreenScaffold(
    topbarTypeCompose: TopbarTypeCompose,
    appBarSetting: AppBarSetting,
    scrollBehavior: TopAppBarScrollBehavior,
    actions: List<ButtonModel> = emptyList(),
    navigationIcon: ButtonModel? = null,
    floatingActionButton: FabIconModel? = null,
    navItems: List<ButtonModel> = emptyList(), // لیست آیتم‌های نویگیشن
    stickyTopbar: (@Composable () -> Unit)? = null,
    startPanel: (@Composable () -> Unit)? = null,
    endPanel: (@Composable () -> Unit)? = null,
    bottomBar: (@Composable () -> Unit)? = null,
    content: @Composable (PaddingValues) -> Unit
) {

    val windowSizeClass = calculateWindowSizeClass()
    val isWideScreen = remember(windowSizeClass) {
        windowSizeClass.widthSizeClass != WindowWidthSizeClass.Compact
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    ProvideLayoutDirection {
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

                val fraction by remember {
                    derivedStateOf { scrollBehavior.state.overlappedFraction }
                }

                val containerColor by remember {
                    derivedStateOf {
                        lerp(
                            appBarSetting.topAppBarConfig.containerColor,
                            appBarSetting.topAppBarConfig.scrolledContainerColor,
                            fraction.coerceIn(0f, 1f)
                        )
                    }
                }

                Column {
                    Surface(
                        Modifier.zIndex(5f),
                        tonalElevation = 0.dp,
                        color = containerColor

                    ) {
                        Column {
                            BaseScreenTopBar(
                                scrollBehavior,
                                appBarSetting.topAppBarConfig,
                                topbarTypeCompose,
                                actions,
                                navigationIcon,
                            )
                            stickyTopbar?.let {
                                stickyTopbar()
                            }


                        }
                    }

                    if (fraction > 0.01f) {
                        UIShadow(TypeShadow.TOP_BAR)
                    } else {
                        UISpacer(5)
                    }
                }

            },
            floatingActionButton = {
                floatingActionButton?.let {
                    BaseScreenFab(it, isWideScreen, navItems)
                }
            },
            // اگر صفحه عریض نبود (موبایل بود)، باتوم بار را نشان بده
            bottomBar = {
                BaseScreenBottomBar(bottomBar, appBarSetting.bottomAppBarConfig, isWideScreen, navItems)
            },
            floatingActionButtonPosition = floatingActionButton?.fabPosition ?: FabPosition.End
        ) { paddingValues ->

            BaseScreenContent(
                navItems,
                startPanel,
                endPanel,
                paddingValues,
                isWideScreen,
                appBarSetting.navigationRailAppBarConfig,
                content
            )
        }
    }
}


