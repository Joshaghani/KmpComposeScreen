package com.github.mohammadjoshaghani.composescreen.commonCompose.toast

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Message
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material.icons.rounded.Event
import androidx.compose.material.icons.rounded.Loop
import androidx.compose.material.icons.rounded.Message
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
import com.github.mohammadjoshaghani.composescreen.commonCompose.UISpacer
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.painterResource

@Composable
fun UIToastNotification() {

    var textToastMessage by remember { mutableStateOf<ToastMessageModel?>(null) }

    LaunchedEffect(Unit) {
        ToastCreator.toastChannel.collectLatest { toast ->
            textToastMessage = toast
            delay(3000)
            textToastMessage = null
        }
    }

    AnimatedVisibility(
        modifier = Modifier.testTag("ToastAnimatedVisibility"),
        visible = textToastMessage != null,
        enter = slideInVertically(
            initialOffsetY = { -it }
        ),
        exit = slideOutVertically(
            targetOffsetY = { -it }
        )
    ) {

        textToastMessage?.apply {
            val textColor = when (state) {
                ToastState.ERROR -> MaterialTheme.colorScheme.error
                ToastState.WARNING -> MaterialTheme.colorScheme.errorContainer
                ToastState.SUCCESS -> MaterialTheme.colorScheme.primary
                ToastState.MESSAGE -> MaterialTheme.colorScheme.onSecondary
            }

            val drawable = when (state) {
                ToastState.ERROR -> Icons.Rounded.Error
                ToastState.WARNING -> Icons.Default.Warning // Res.drawable.ic_law
                ToastState.SUCCESS -> Icons.Rounded.Event // Res.drawable.ic_shield_tick
                ToastState.MESSAGE -> Icons.AutoMirrored.Rounded.Message // Res.drawable.ic_info_circle
            }

            val message = message

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
                    Row(
                        modifier = Modifier
                            .heightIn(min = 60.dp)
                            .clip(MaterialTheme.shapes.medium)
                            .background(MaterialTheme.colorScheme.background)
                            .border(
                                1.dp,
                                textColor,
                                MaterialTheme.shapes.medium
                            ),

                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {

                        UISpacer(16)

                        Text(message)

                        UISpacer(8)

                        Icon(
                            imageVector = drawable,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            tint = textColor
                        )

                        UISpacer(16)

                    }
                }
            }
        }
    }
}