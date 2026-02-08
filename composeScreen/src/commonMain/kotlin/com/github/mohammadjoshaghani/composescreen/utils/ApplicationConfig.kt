package com.github.mohammadjoshaghani.composescreen.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator

object ApplicationConfig {
    lateinit var config: Config
    var navigator: Navigator? = null
    var bottomSheetNavigator: BottomSheetNavigator? = null
    var animationType by mutableStateOf(ScreenTransitionType.SLIDE)
    lateinit var errorScreen: @Composable (message: String, retryClick: () -> Unit) -> Unit
    var loadingScreen: (@Composable () -> Unit)? = null

}

