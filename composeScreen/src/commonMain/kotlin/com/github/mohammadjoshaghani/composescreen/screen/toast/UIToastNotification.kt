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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.app.ProvideLayoutDirection
import com.github.mohammadjoshaghani.composescreen.component.UISpacer
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.painterResource

@Composable
fun UIToastNotification() {

    var textToastMessage by remember { mutableStateOf<ToastMessageModel?>(null) }
    var isShowToast by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        ToastCreator.toastChannel.collectLatest { toast ->
            textToastMessage = toast
            isShowToast = true
            delay(3000)
            isShowToast = false
        }
    }

    AnimatedVisibility(
        modifier = Modifier
            .testTag("ToastAnimatedVisibility"),
        visible = isShowToast,
        enter = slideInVertically(
            initialOffsetY = { -it }
        ),
        exit = slideOutVertically(
            targetOffsetY = { -(it + 10) }
        )
    ) {

        textToastMessage!!.apply {

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
                    ProvideLayoutDirection {
                        Row(
                            modifier = Modifier
                                .heightIn(min = 60.dp)
                                .clip(MaterialTheme.shapes.medium)
                                .background(MaterialTheme.colorScheme.background)
                                .border(
                                    1.dp,
                                    state.textColor,
                                    MaterialTheme.shapes.medium
                                ),

                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {

                            UISpacer(16)

                            state.icon?.let {
                                Icon(
                                    imageVector = state.icon,
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp),
                                    tint = state.textColor,
                                )
                            } ?: run {
                                state.vector?.let {
                                    Icon(
                                        painter = painterResource(it),
                                        contentDescription = null,
                                        modifier = Modifier.size(24.dp),
                                        tint = state.textColor,
                                    )
                                }
                            }

                            UISpacer(8)

                            Text(message)

                            UISpacer(16)

                        }
                    }
                }
            }
        }
    }
}