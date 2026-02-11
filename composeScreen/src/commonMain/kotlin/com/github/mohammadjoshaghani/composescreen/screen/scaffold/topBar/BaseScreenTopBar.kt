package com.github.mohammadjoshaghani.composescreen.screen.scaffold.topBar

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.basicMarquee
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.github.mohammadjoshaghani.composescreen.component.image.IconSourceType
import com.github.mohammadjoshaghani.composescreen.component.button.IconButton.UIIconButton
import com.github.mohammadjoshaghani.composescreen.component.button.IconButton.IconButtonModel
import com.github.mohammadjoshaghani.composescreen.screen.base.IBaseScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseScreenTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    topbarModel: TopbarModel,
    actions: List<IconButtonModel> = emptyList(),
    navigationIcon: IconButtonModel? = null,
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
        TopAppBar(
            scrollBehavior = scrollBehavior,
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
                    UIIconButton(icon)
                }
            },
            navigationIcon = {
                navigationIcon?.let { icon ->
                    UIIconButton(icon)

                } ?: run {
                    val navigator = LocalNavigator.currentOrThrow
                    if (navigator.size > 1) {
                        UIIconButton(
                            IconButtonModel(
                                icon = IconSourceType.IconVector(Icons.AutoMirrored.Rounded.ArrowBack)
                            ) {
                                (navigator.lastItemOrNull as? IBaseScreen)?.onBackPressed()
                            }
                        )
                    }
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface,
//                scrolledContainerColor = MaterialTheme.colorScheme.primary,
            ),
        )
    }

}