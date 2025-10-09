package com.github.mohammadjoshaghani.composescreen.base.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowScrollAwareFadingHeader
import com.github.mohammadjoshaghani.composescreen.base.navigation.Navigator
import com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen.compose.StickyHeaderState
import com.github.mohammadjoshaghani.composescreen.compose.bottomSheet.IBottomSheet
import com.github.mohammadjoshaghani.composescreen.compose.dialog.UIAlertDialog
import com.github.mohammadjoshaghani.composescreen.compose.dialog.base.IBaseDialog
import com.github.mohammadjoshaghani.composescreen.utils.ScreenSize
import kotlinx.coroutines.flow.MutableStateFlow

interface IRootScreen {

    var isVisibleAnimation: MutableStateFlow<Boolean>

    val animationTime: Long
        get() = 150L

    var showAnimation: Boolean

    val stickyState: StickyHeaderState
        get() = StickyHeaderState()

    val screenSize: MutableState<ScreenSize>
        get() = mutableStateOf(ScreenSize(0.dp, 0.dp))

    val showAwareHeader: MutableState<Boolean>
        get() = mutableStateOf(this is IShowScrollAwareFadingHeader)

    val heightAwareFaideHeader: MutableState<Dp>
        get() = mutableStateOf(0.dp)

    var result: List<Any>?

    fun show(replace: Boolean = false, animation: Boolean = true)

    fun onStart() {}

    fun onResume() {}

    fun onRestart(vararg result: Any?) {
    }

    fun setReuslt(vararg result: Any) {
        Navigator.previous()?.result = result.toList()
    }

    private fun cleanupResources() {
        UIAlertDialog.getDialog()?.dismiss()
        IBaseDialog.stack.clear()
        IBottomSheet.stack.clear()
    }

    fun onPause() {
        cleanupResources()
    }

    fun onDestroy() {
        cleanupResources()
    }

    fun onBackPressed(
        isCloseDialogAndBackScreen: Boolean = false,
    ): Boolean {

        return when {
            isCloseDialogAndBackScreen -> {
                UIAlertDialog.getDialog()?.dismiss()
                IBaseDialog.stack.clear()
                IBottomSheet.stack.clear()
                Navigator.pop()
            }

            UIAlertDialog.isShow() -> {
                UIAlertDialog.getDialog()?.dismiss()
                true
            }

            IBaseDialog.stack.isNotEmpty() -> {
                IBaseDialog.stack.clear()
                true
            }

            IBottomSheet.stack.isNotEmpty() -> {
                IBottomSheet.stack.clear()
                true
            }

            else -> Navigator.pop()
        }


    }

    @Composable
    fun ShowScreenFromApp()

}