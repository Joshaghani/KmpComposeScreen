package com.github.mohammadjoshaghani.composescreen.compose.dialog.base

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.github.mohammadjoshaghani.composescreen.app.ProvideLayoutDirection
import com.github.mohammadjoshaghani.composescreen.base.BaseHandler
import com.github.mohammadjoshaghani.composescreen.base.BaseViewModel
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewEvent
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewSideEffect
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewState
import com.github.mohammadjoshaghani.composescreen.base.navigation.Navigator
import com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen.ErrorShell
import com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen.LoadingShell
import com.github.mohammadjoshaghani.composescreen.compose.dialog.base.IBaseDialog.Companion.stack
import com.github.mohammadjoshaghani.composescreen.compose.toast.UIToastNotification
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseDialog<State : ViewState<Event>, Event : ViewEvent, Effect : ViewSideEffect, VM : BaseViewModel<Event, State, Effect>> :
    IBaseDialog {

    abstract val viewModel: VM
    abstract val handler: BaseHandler<VM, Effect, Event>

    var onEventSent: (Event) -> Unit = { viewModel.setEvent(it) }

    private val isShowDialogFlow = MutableStateFlow(true)

    private var setCanceledOnTouchOutside: Boolean = true

    fun setCanceledOnTouchOutside(value: Boolean) = apply {
        setCanceledOnTouchOutside = value
    }

    override fun show() {
        stack.add(this)
        viewModel.initViewModel()
    }

    @Composable
    override fun ShowDialog() {
        LaunchedEffect(viewModel) {
            viewModel.effect.collect { handler.handleEffects(it, viewModel) }
        }

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
                    Column {
                        when {
                            viewModel.viewState.value.isLoading -> LoadingShell()
                            viewModel.viewState.value.errorScreen != null -> {
                                val e = viewModel.viewState.value.errorScreen!!
                                ErrorShell(e.message) { viewModel.setEvent(e.event) }
                            }
                        }
                        ComposeView(viewModel.viewState.value)
                    }

                    UIToastNotification()
                }
            }
        }

    }


    @Composable
    abstract fun ComposeView(state: State)

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