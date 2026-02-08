package com.github.mohammadjoshaghani.composescreen.screen.base

import cafe.adriel.voyager.core.screen.Screen
import com.github.mohammadjoshaghani.composescreen.dialog.alertDialog.UIAlertDialog
import com.github.mohammadjoshaghani.composescreen.dialog.base.IBaseDialog
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig.navigator
import com.github.mohammadjoshaghani.composescreen.utils.ScreenTransitionType

class IBaseScreenImpl : IBaseScreen {
    private lateinit var screen: Screen

    override fun show(
        replace: Boolean,
        animation: ScreenTransitionType
    ) {
        ApplicationConfig.animationType = animation
        if (replace) navigator?.replace(getScreen()) else navigator?.push(getScreen())
    }

    override fun onBackPressed(
        isCloseDialogAndBackScreen: Boolean
    ): Boolean {

        return when {
            isCloseDialogAndBackScreen -> {
                UIAlertDialog.getDialog()?.dismiss()
                IBaseDialog.stack.clear()
                navigator!!.pop()
            }

            UIAlertDialog.isShow() -> {
                UIAlertDialog.getDialog()?.dismiss()
                true
            }

            IBaseDialog.stack.isNotEmpty() -> {
                IBaseDialog.stack.clear()
                true
            }

            else -> {
                ApplicationConfig.animationType = ScreenTransitionType.SLIDE
                navigator!!.pop()
            }
        }
    }

    override fun setScreen(screen: Screen) {
        this.screen = screen
    }

    override fun getScreen(): Screen {
        return this.screen
    }
}