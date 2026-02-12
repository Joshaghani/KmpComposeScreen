package com.github.mohammadjoshaghani.composescreen.screen.scaffold.topBar

import androidx.compose.foundation.basicMarquee
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.github.mohammadjoshaghani.composescreen.component.button.IconButton.ButtonModel
import com.github.mohammadjoshaghani.composescreen.component.button.IconButton.UIIconButton
import com.github.mohammadjoshaghani.composescreen.component.button.UIButton
import com.github.mohammadjoshaghani.composescreen.screen.base.IBaseScreen
import com.github.mohammadjoshaghani.composescreen.utils.TopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseScreenTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    topAppBar: TopAppBar,
    topbarModel: TopbarModel,
    actions: List<ButtonModel> = emptyList(),
    navigationIcon: ButtonModel? = null,
) {

    if (
        actions.isEmpty() &&
        navigationIcon == null &&
        topbarModel == TopbarModel.Nothing
    ) {
        return
    }

    val fraction = scrollBehavior.state.overlappedFraction

    Surface(
        shadowElevation = if (fraction > 0.01f) 8.dp else 0.dp,
        tonalElevation = 0.dp // اگر نمی‌خواهید رنگش با ارتفاع عوض شود، این را 0 بگذارید
    ) {

        when (topAppBar.topbarType) {
            TopbarType.NORMAL -> NormalAppbar(
                scrollBehavior = scrollBehavior,
                topbarModel = topbarModel,
                topAppBar = topAppBar,
                actions = actions,
                navigationIcon = navigationIcon,
            )

            TopbarType.MEDIUM -> MediumAppbar(
                scrollBehavior = scrollBehavior,
                topbarModel = topbarModel,
                topAppBar = topAppBar,
                actions = actions,
                navigationIcon = navigationIcon,
            )

            TopbarType.LARGE -> LargeAppbar(
                scrollBehavior = scrollBehavior,
                topbarModel = topbarModel,
                topAppBar = topAppBar,
                actions = actions,
                navigationIcon = navigationIcon,
            )
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NormalAppbar(
    scrollBehavior: TopAppBarScrollBehavior,
    topbarModel: TopbarModel,
    topAppBar: TopAppBar,
    actions: List<ButtonModel> = emptyList(),
    navigationIcon: ButtonModel? = null,
) {
    TopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            when (topbarModel) {
                is TopbarModel.Compose -> topbarModel.content()
                TopbarModel.Nothing -> Unit
                is TopbarModel.Text -> Text(
                    topbarModel.title,
                    maxLines = 1,
                    modifier = Modifier.basicMarquee(iterations = Int.MAX_VALUE)
                )
            }
        },
        actions = {
            actions.forEach { icon ->
                UIButton(icon)
            }
        },
        navigationIcon = {
            navigationIcon?.let { icon ->
                UIButton(icon)

            } ?: run {
                val navigator = LocalNavigator.currentOrThrow
                if (navigator.size > 1) {
                    UIIconButton(
                        icon = Icons.AutoMirrored.Rounded.ArrowBack
                    ) {
                        (navigator.lastItemOrNull as? IBaseScreen)?.onBackPressed()
                    }

                }
            }
        },
        colors = topAppBarColors(
            containerColor = topAppBar.containerColor,
            scrolledContainerColor = topAppBar.scrolledContainerColor
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediumAppbar(
    scrollBehavior: TopAppBarScrollBehavior,
    topbarModel: TopbarModel,
    topAppBar: TopAppBar,
    actions: List<ButtonModel> = emptyList(),
    navigationIcon: ButtonModel? = null,
) {
    MediumTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            when (topbarModel) {
                is TopbarModel.Compose -> topbarModel.content()
                TopbarModel.Nothing -> Unit
                is TopbarModel.Text -> Text(
                    topbarModel.title,
                    maxLines = 1,
                    modifier = Modifier.basicMarquee(iterations = Int.MAX_VALUE)
                )
            }
        },
        actions = {
            actions.forEach { icon ->
                UIButton(icon)
            }
        },
        navigationIcon = {
            navigationIcon?.let { icon ->
                UIButton(icon)

            } ?: run {
                val navigator = LocalNavigator.currentOrThrow
                if (navigator.size > 1) {
                    UIIconButton(
                        icon = Icons.AutoMirrored.Rounded.ArrowBack
                    ) {
                        (navigator.lastItemOrNull as? IBaseScreen)?.onBackPressed()
                    }

                }
            }
        },
        colors = topAppBarColors(
            containerColor = topAppBar.containerColor,
            scrolledContainerColor = topAppBar.scrolledContainerColor
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LargeAppbar(
    scrollBehavior: TopAppBarScrollBehavior,
    topbarModel: TopbarModel,
    topAppBar: TopAppBar,
    actions: List<ButtonModel> = emptyList(),
    navigationIcon: ButtonModel? = null,
) {
    LargeTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            when (topbarModel) {
                is TopbarModel.Compose -> topbarModel.content()
                TopbarModel.Nothing -> Unit
                is TopbarModel.Text -> Text(
                    topbarModel.title,
                    maxLines = 1,
                    modifier = Modifier.basicMarquee(iterations = Int.MAX_VALUE)
                )
            }
        },
        actions = {
            actions.forEach { icon ->
                UIButton(icon)
            }
        },
        navigationIcon = {
            navigationIcon?.let { icon ->
                UIButton(icon)

            } ?: run {
                val navigator = LocalNavigator.currentOrThrow
                if (navigator.size > 1) {
                    UIIconButton(
                        icon = Icons.AutoMirrored.Rounded.ArrowBack
                    ) {
                        (navigator.lastItemOrNull as? IBaseScreen)?.onBackPressed()
                    }
                }
            }
        },
        colors = topAppBarColors(
            containerColor = topAppBar.containerColor,
            scrolledContainerColor = topAppBar.scrolledContainerColor
        )
    )
}

