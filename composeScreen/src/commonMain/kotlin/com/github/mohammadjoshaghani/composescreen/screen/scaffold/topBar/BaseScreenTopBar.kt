package com.github.mohammadjoshaghani.composescreen.screen.scaffold.topBar

import androidx.compose.foundation.basicMarquee
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.github.mohammadjoshaghani.composescreen.component.clickableIcon.ClickableIcon
import com.github.mohammadjoshaghani.composescreen.component.clickableIcon.IClickableIconModel
import com.github.mohammadjoshaghani.composescreen.screen.base.IBaseScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseScreenTopBar(
    topbarModel: TopbarModel,
    isScrolled: Boolean,
    actions: List<IClickableIconModel> = emptyList(),
    navigationIcon: IClickableIconModel? = null,
) {

    if (
        actions.isEmpty() &&
        navigationIcon == null &&
        topbarModel == TopbarModel.Nothing
    ) {
        return
    }

    Surface(
        shadowElevation = if (isScrolled) 4.dp else 0.dp,
        tonalElevation = if (isScrolled) 2.dp else 0.dp
    ) {
        TopAppBar(
            title = {
                when (topbarModel) {
                    is TopbarModel.Compose -> topbarModel.content()
                    TopbarModel.Nothing -> Unit
                    is TopbarModel.Text -> Text(
                        topbarModel.title,
                        maxLines = 1,
                        modifier = Modifier.basicMarquee()
                    )
                }
            },
            actions = {
                actions.forEach { icon ->
                    when (icon) {
                        is IClickableIconModel.ClickableIconModel -> {
                            ClickableIcon(
                                icon.iconId,
                                title = icon.title,
                                doesButtonHaveBorder = icon.doesButtonHaveBorder,
                                badgeCount = icon.badgeCount,
                                onClick = icon.onIconPressed,
                                tint = icon.tint ?: MaterialTheme.colorScheme.onBackground
                            )
                        }

                        is IClickableIconModel.ClickableIconVectorModel -> {
                            ClickableIcon(
                                icon.iconId,
                                title = icon.title,
                                doesButtonHaveBorder = icon.doesButtonHaveBorder,
                                onClick = icon.onIconPressed,
                                badgeCount = icon.badgeCount,
                                tint = icon.tint ?: MaterialTheme.colorScheme.onBackground
                            )
                        }

                        else -> {}
                    }
                }
            },
            navigationIcon = {
                navigationIcon?.let { icon ->
                    when (icon) {
                        is IClickableIconModel.ClickableIconModel -> {
                            ClickableIcon(
                                icon.iconId,
                                title = icon.title,
                                doesButtonHaveBorder = icon.doesButtonHaveBorder,
                                badgeCount = icon.badgeCount,
                                onClick = icon.onIconPressed,
                                tint = icon.tint ?: MaterialTheme.colorScheme.onBackground
                            )
                        }

                        is IClickableIconModel.ClickableIconVectorModel -> {
                            ClickableIcon(
                                icon.iconId,
                                title = icon.title,
                                doesButtonHaveBorder = icon.doesButtonHaveBorder,
                                onClick = icon.onIconPressed,
                                badgeCount = icon.badgeCount,
                                tint = icon.tint ?: MaterialTheme.colorScheme.onBackground
                            )
                        }

                        else -> {}
                    }
                } ?: run {
                    val navigator = LocalNavigator.currentOrThrow
                    if (navigator.size > 1) {
                        ClickableIcon(icon = Icons.AutoMirrored.Rounded.ArrowBack) {
                            (navigator.lastItemOrNull as? IBaseScreen)?.onBackPressed()
                        }
                    }
                }
            }
        )
    }
}