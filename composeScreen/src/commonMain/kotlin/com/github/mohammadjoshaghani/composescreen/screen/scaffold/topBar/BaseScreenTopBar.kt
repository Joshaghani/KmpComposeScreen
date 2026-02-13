package com.github.mohammadjoshaghani.composescreen.screen.scaffold.topBar

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.github.mohammadjoshaghani.composescreen.component.button.IconButton.ButtonModel
import com.github.mohammadjoshaghani.composescreen.component.button.IconButton.UIIconButton
import com.github.mohammadjoshaghani.composescreen.component.button.UIButton
import com.github.mohammadjoshaghani.composescreen.screen.base.IBaseScreen
import com.github.mohammadjoshaghani.composescreen.utils.TopAppBarConfig

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseScreenTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    topAppBarConfig: TopAppBarConfig,
    topbarModel: TopbarTypeCompose,
    actions: List<ButtonModel> = emptyList(),
    navigationIcon: ButtonModel? = null,
) {

    if (actions.isEmpty() && navigationIcon == null && topbarModel == TopbarTypeCompose.Nothing) return

    AppBarByType(
        scrollBehavior = scrollBehavior,
        topbarTypeCompose = topbarModel,
        topAppBar = topAppBarConfig,
        actions = actions,
        navigationIcon = navigationIcon,
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBarByType(
    scrollBehavior: TopAppBarScrollBehavior,
    topbarTypeCompose: TopbarTypeCompose,
    topAppBar: TopAppBarConfig,
    actions: List<ButtonModel>,
    navigationIcon: ButtonModel?,
) {
    val titleContent: @Composable () -> Unit = {
        when (topbarTypeCompose) {
            is TopbarTypeCompose.Compose -> topbarTypeCompose.content()
            TopbarTypeCompose.Nothing -> Unit
            is TopbarTypeCompose.Text -> Text(
                text = topbarTypeCompose.title,
                maxLines = 1,
                modifier = Modifier.basicMarquee(iterations = Int.MAX_VALUE)
            )
        }
    }

    val actionsContent: @Composable RowScope.() -> Unit = {
        actions.forEach { UIButton(it) }
    }

    val navIconContent: @Composable () -> Unit = {
        navigationIcon?.let { UIButton(it) } ?: DefaultBackButtonIfPossible()
    }

    val colors = topAppBarColors(
        containerColor = topAppBar.containerColor,
        scrolledContainerColor = topAppBar.scrolledContainerColor
    )

    when (topAppBar.topbarType) {
        TopbarType.NORMAL -> TopAppBar(
            scrollBehavior = scrollBehavior,
            title = titleContent,
            actions = actionsContent,
            navigationIcon = navIconContent,
            colors = colors,
        )

        TopbarType.MEDIUM -> MediumTopAppBar(
            scrollBehavior = scrollBehavior,
            title = titleContent,
            actions = actionsContent,
            navigationIcon = navIconContent,
            colors = colors,
        )

        TopbarType.LARGE -> LargeTopAppBar(
            scrollBehavior = scrollBehavior,
            title = titleContent,
            actions = actionsContent,
            navigationIcon = navIconContent,
            colors = colors,
        )
    }
}

@Composable
private fun DefaultBackButtonIfPossible() {
    val navigator = LocalNavigator.currentOrThrow
    if (navigator.size <= 1) return

    UIIconButton(icon = Icons.AutoMirrored.Rounded.ArrowBack) {
        (navigator.lastItemOrNull as? IBaseScreen)?.onBackPressed()
    }
}

