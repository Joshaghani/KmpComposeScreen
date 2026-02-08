package com.github.mohammadjoshaghani.composescreen.component

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.stack.StackEvent
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.ScreenTransition


@Composable
fun SlidScreenAnimation(navigator: Navigator) {
    ScreenTransition(
        navigator = navigator,
        transition = {
            // تعریف آفست‌ها برای حرکت چپ به راست
            val (initialOffset, targetOffset) = when (navigator.lastEvent) {
                StackEvent.Pop -> ({ size: Int -> size }) to ({ size: Int -> -size })
                else -> ({ size: Int -> -size }) to ({ size: Int -> size })
            }

            // نکته مهم: نام پارامترها حتماً باید initialOffsetX و targetOffsetX باشد
            slideInHorizontally(
                initialOffsetX = initialOffset,
                animationSpec = tween(300) // می‌توانید سرعت را اینجا تنظیم کنید
            ) togetherWith slideOutHorizontally(
                targetOffsetX = targetOffset,
                animationSpec = tween(300)
            )
        }
    )
}
