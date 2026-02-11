package com.github.mohammadjoshaghani.composescreen.screen.scaffold

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.github.mohammadjoshaghani.composescreen.component.clickableIcon.IClickableIconModel
import com.github.mohammadjoshaghani.composescreen.app.ProvideLayoutDirection
import com.github.mohammadjoshaghani.composescreen.extension.noRippleClickable
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.bottomBar.BaseScreenBottomBar
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.fab.BaseScreenFab
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.fab.FabIconModel
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.topBar.BaseScreenTopBar
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.bottomBar.NavigationItem
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.topBar.TopbarModel


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun BaseScreenScaffold(
    topbarModel: TopbarModel,
    isScrolled: Boolean,
    actions: List<IClickableIconModel> = emptyList(),
    navigationIcon: IClickableIconModel? = null,
    floatingActionButton: FabIconModel? = null,
    navItems: List<NavigationItem> = emptyList(), // لیست آیتم‌های نویگیشن
    startPanel: (@Composable () -> Unit)? = null,
    endPanel: (@Composable () -> Unit)? = null,
    bottomBar: (@Composable () -> Unit)? = null,
    content: @Composable (PaddingValues) -> Unit
) {

    val windowSizeClass = calculateWindowSizeClass()
    val isWideScreen = windowSizeClass.widthSizeClass != WindowWidthSizeClass.Compact

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    ProvideLayoutDirection {
        Scaffold(
            modifier = Modifier
                .noRippleClickable {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                }
                .imePadding(),
            topBar = {
                BaseScreenTopBar(
                    topbarModel,
                    isScrolled,
                    actions,
                    navigationIcon,
                )
            },
            floatingActionButton = {
                floatingActionButton?.let {
                    BaseScreenFab(it, isWideScreen, navItems)
                }
            },
            // اگر صفحه عریض نبود (موبایل بود)، باتوم بار را نشان بده
            bottomBar = {
                BaseScreenBottomBar(bottomBar, isWideScreen, navItems)
            },
            floatingActionButtonPosition = floatingActionButton?.fabPosition ?: FabPosition.End
        ) { paddingValues ->

            BaseScreenContent(
                navItems,
                startPanel,
                endPanel,
                paddingValues,
                isWideScreen,
                content
            )
        }
    }
}


