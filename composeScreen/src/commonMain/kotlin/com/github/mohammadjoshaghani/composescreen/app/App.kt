package com.github.mohammadjoshaghani.composescreen.app

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
//import cafe.adriel.voyager.jetpack.ProvideNavigatorLifecycleKMPSupport
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import cafe.adriel.voyager.transitions.FadeTransition
import cafe.adriel.voyager.transitions.ScaleTransition
import cafe.adriel.voyager.transitions.SlideTransition
import com.github.mohammadjoshaghani.composescreen.component.SlidScreenAnimation
import com.github.mohammadjoshaghani.composescreen.screen.base.IBaseScreen
import com.github.mohammadjoshaghani.composescreen.screen.toast.UIToastNotification
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig
import com.github.mohammadjoshaghani.composescreen.utils.ScreenTransitionType

@OptIn(ExperimentalMaterialApi::class, ExperimentalVoyagerApi::class)
@Composable
@Preview
fun App(startScreen: List<IBaseScreen>) {
        BottomSheetNavigator(
            sheetShape = RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 16.dp
            )
        ) {

            ApplicationConfig.bottomSheetNavigator = LocalBottomSheetNavigator.current

            Navigator(
                screens = startScreen.map { it.getScreen() },
                onBackPressed = {
                    (it as? IBaseScreen)?.onBackPressed() ?: true
                }
            ) { navigator ->
                DisposableEffect(navigator) {
                    ApplicationConfig.navigator = navigator
                    onDispose {
                        ApplicationConfig.navigator = null
                        ApplicationConfig.bottomSheetNavigator = null
                    }
                }

                when (ApplicationConfig.animationType) {
                    ScreenTransitionType.SLIDE -> {
                        if (ApplicationConfig.isRtl) {
                            SlidScreenAnimation(navigator)
                        } else {
                            SlideTransition(navigator = navigator)
                        }
                    }

                    ScreenTransitionType.FADE -> FadeTransition(navigator)
                    ScreenTransitionType.SCALE -> ScaleTransition(navigator)
                    ScreenTransitionType.NONE -> CurrentScreen()
                }

            }

    }

    UIToastNotification()

}

