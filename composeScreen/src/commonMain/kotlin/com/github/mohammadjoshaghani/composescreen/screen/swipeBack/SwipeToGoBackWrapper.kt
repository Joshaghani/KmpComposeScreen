package com.github.mohammadjoshaghani.composescreen.screen.swipeBack

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig
import kotlin.math.abs

@Composable
fun SwipeToGoBackWrapper(
    thresholdDp: Float = 72f, // dp، نه px
    content: @Composable () -> Unit,
) {
    val layoutDirection = LocalLayoutDirection.current
    val density = LocalDensity.current

    // drag به px است
    var totalDragPx by remember { mutableFloatStateOf(0f) }
    var showIcon by remember { mutableStateOf(false) }

    // threshold رو به px تبدیل می‌کنیم تا مقایسه درست باشه
    val thresholdPx = remember(thresholdDp, density) {
        with(density) { thresholdDp.dp.toPx() }
    }

    val iconOffset by animateDpAsState(
        targetValue = if (showIcon) 24.dp else (-200).dp,
        animationSpec = spring(),
        label = "SwipeBackIconOffset"
    )

    val iconAlignment = if (layoutDirection == LayoutDirection.Rtl) {
        Alignment.CenterEnd
    } else {
        Alignment.CenterStart
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(layoutDirection, thresholdPx) {
                detectHorizontalDragGestures(
                    onDragStart = {
                        totalDragPx = 0f
                        showIcon = false
                    },
                    onHorizontalDrag = { change, dragAmountPx ->
                        change.consume()
                        totalDragPx += dragAmountPx

                        // فقط وقتی درگ در جهت "back" هست آیکن رو نشون بده
                        showIcon = isBackDirectionDrag(layoutDirection, totalDragPx)
                    },
                    onDragEnd = {
                        val shouldNavigateBack = when (layoutDirection) {
                            LayoutDirection.Rtl -> totalDragPx < -thresholdPx
                            LayoutDirection.Ltr -> totalDragPx > thresholdPx
                        }

                        if (shouldNavigateBack) {
                            ApplicationConfig.navigator?.pop()
                        }

                        totalDragPx = 0f
                        showIcon = false
                    }
                )
            },
        contentAlignment = Alignment.Center
    ) {
        content()

        Icon(
            imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
            contentDescription = "Back",
            modifier = Modifier
                .align(iconAlignment)
                .offset(x = iconOffset)
        )
    }
}

private fun isBackDirectionDrag(layoutDirection: LayoutDirection, totalDragPx: Float): Boolean {
    return when (layoutDirection) {
        LayoutDirection.Rtl -> totalDragPx < 0f
        LayoutDirection.Ltr -> totalDragPx > 0f
    } && abs(totalDragPx) > 6f // deadzone کوچیک برای جلوگیری از flicker
}