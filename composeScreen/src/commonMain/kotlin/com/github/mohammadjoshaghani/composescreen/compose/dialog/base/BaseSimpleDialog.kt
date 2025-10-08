package com.github.mohammadjoshaghani.composescreen.compose.dialog.base

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.github.mohammadjoshaghani.composescreen.app.ProvideLayoutDirection
import com.github.mohammadjoshaghani.composescreen.base.navigation.Navigator
import com.github.mohammadjoshaghani.composescreen.compose.dialog.base.IBaseDialog.Companion.stack
import com.github.mohammadjoshaghani.composescreen.compose.toast.UIToastNotification
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseSimpleDialog : IBaseDialog {

    private val isShowDialogFlow = MutableStateFlow(true)

    private var setCanceledOnTouchOutside: Boolean = true

    fun setCanceledOnTouchOutside(value: Boolean) = apply {
        setCanceledOnTouchOutside = value
    }

    override fun show() {
        stack.add(this)
    }

    @Composable
    override fun ShowDialog() {
        Dialog(
            properties = DialogProperties(
                dismissOnClickOutside = setCanceledOnTouchOutside,
                usePlatformDefaultWidth = false
            ),
            onDismissRequest = { dismiss() }
        ) {
            ProvideLayoutDirection {
                Box(
                    modifier = Modifier.Companion
                        .fillMaxSize()
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            if (setCanceledOnTouchOutside) {
                                dismiss()
                            }
                        }
                ) {
                    Column { ComposeView() }

                    UIToastNotification()
                }
            }
        }
    }

    @Composable
    abstract fun ComposeView()

    fun dismiss() {
        stack.remove(this)
        isShowDialogFlow.value = false
    }


    fun onDismissRequest(action: () -> Unit) = apply {
        Navigator.state.current.value?.viewModel?.launchOnScope {
            isShowDialogFlow.collect {
                if (!it) action()
            }
        }
    }

}