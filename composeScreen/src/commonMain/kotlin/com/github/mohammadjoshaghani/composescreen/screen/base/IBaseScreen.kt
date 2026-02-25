package com.github.mohammadjoshaghani.composescreen.screen.base

import cafe.adriel.voyager.core.screen.Screen
import com.github.mohammadjoshaghani.composescreen.utils.ScreenTransitionType

interface IBaseScreen {
    fun show(replace: Boolean = false, animation: ScreenTransitionType = ScreenTransitionType.SLIDE)
    fun onBackPressed(isCloseDialogAndBackScreen: Boolean = false): Boolean
    fun getScreen(): Screen

    fun setScreen(screen: Screen)

    fun onStart() {}
    fun onStop() {}

    fun onRestart() {}
}