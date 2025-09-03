package com.github.mohammadjoshaghani.composescreen.commonCompose.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowStickyHeader
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowTopbarMain
import com.github.mohammadjoshaghani.composescreen.base.navigation.Navigator
import com.github.mohammadjoshaghani.composescreen.commonCompose.clickableIcon.ClickableIcon
import com.github.mohammadjoshaghani.composescreen.commonCompose.clickableIcon.IClickableIconModel
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig
import org.jetbrains.compose.resources.painterResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowTitleMain(scrollBehavior: TopAppBarScrollBehavior, isScrolled: Boolean) {
    val screen = Navigator.state.current.value

    if (screen is IShowTopbarMain) {
        if (screen.customTopbarUI(scrollBehavior) != null) {
            screen.customTopbarUI(scrollBehavior)!!.invoke()
        } else {
            CenterAlignedTopAppBar(
                title = {
                    screen.titleTopBar().invoke()
                },
                navigationIcon = {
                    screen.menuIconTopBar()?.let { icon ->
                        when (icon) {
                            is IClickableIconModel.ClickableIconModel -> ClickableIcon(
                                icon.iconId,
                                title = icon.title,
                                badgeCount = icon.badgeCount,
                                onClick = icon.onIconPressed
                            )

                            is IClickableIconModel.ClickableIconVectorModel -> {
                                ClickableIcon(
                                    icon.iconId,
                                    title = icon.title,
                                    badgeCount = icon.badgeCount,
                                    onClick = icon.onIconPressed
                                )
                            }
                        }
                    }
                },
                actions = {
                    screen.actionIconsTopBar().forEach { icon ->
                        when (icon) {
                            is IClickableIconModel.ClickableIconModel -> ClickableIcon(
                                icon.iconId,
                                title = icon.title,
                                badgeCount = icon.badgeCount,
                                onClick = icon.onIconPressed
                            )

                            is IClickableIconModel.ClickableIconVectorModel -> {
                                ClickableIcon(
                                    icon.iconId,
                                    title = icon.title,
                                    badgeCount = icon.badgeCount,
                                    onClick = icon.onIconPressed
                                )
                            }
                        }
                    }
                },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.topAppBarColors(
                    scrolledContainerColor = ApplicationConfig.config.color.background,
                    containerColor = ApplicationConfig.config.color.background,
                )
            )
        }
    }

    if (isScrolled && ApplicationConfig.config.isDarkTheme) {
        if (screen is IShowStickyHeader) {
            if (!screen.stickyState.hasStickyHeader.collectAsState().value) {
                HorizontalDivider()
            }
        } else {
            HorizontalDivider()
        }
    }
}