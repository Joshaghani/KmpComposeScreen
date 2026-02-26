package com.github.mohammadjoshaghani.composescreen.screen.toast

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter.Companion.tint
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.app.ProvideLayoutDirection
import com.github.mohammadjoshaghani.composescreen.component.UISpacer
import com.github.mohammadjoshaghani.composescreen.component.image.UIIcon
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.painterResource

private const val TOAST_DURATION_MS = 3000L

@Composable
fun UIToastNotification() {
    var toast by remember { mutableStateOf<ToastMessageModel?>(null) }
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        ToastCreator.toastChannel.collectLatest { incoming ->
            toast = incoming
            visible = true
            delay(TOAST_DURATION_MS)
            visible = false
        }
    }

    AnimatedVisibility(
        modifier = Modifier.testTag("ToastAnimatedVisibility"),
        visible = visible && toast != null,
        enter = slideInVertically(initialOffsetY = { -it }),
        exit = slideOutVertically(targetOffsetY = { -(it + 10) })
    ) {
        val model = toast ?: return@AnimatedVisibility

        ToastCard(model = model)
    }
}

@Composable
private fun ToastCard(model: ToastMessageModel) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.shadow(
                ambientColor = MaterialTheme.colorScheme.onBackground,
                elevation = 5.dp,
                shape = MaterialTheme.shapes.medium
            ),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = MaterialTheme.shapes.medium
        ) {
            ProvideLayoutDirection(ApplicationConfig.isRtl) {
                Row(
                    modifier = Modifier
                        .heightIn(min = 60.dp)
                        .clip(MaterialTheme.shapes.medium)
                        .background(MaterialTheme.colorScheme.background)
                        .border(
                            width = 1.dp,
                            color = model.state.textColor,
                            shape = MaterialTheme.shapes.medium
                        )
                        .padding(vertical = 10.dp), // کمی نفس دادن به محتوا
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    UISpacer(16)

                    ToastIcon(state = model.state)

                    UISpacer(8)

                    Text(
                        text = model.message,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    UISpacer(16)
                }
            }
        }
    }
}

@Composable
private fun ToastIcon(state: ToastState) {
    when {
        state.icon != null -> UIIcon(
            icon = state.icon,
            modifier = Modifier.size(24.dp),
            tint = state.textColor
        )

        state.vector != null -> UIIcon(
            drawable = state.vector,
            modifier = Modifier.size(24.dp),
            tint = state.textColor
        )

        else -> Unit
    }
}