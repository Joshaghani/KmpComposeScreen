package com.github.mohammadjoshaghani.composescreen.compose.bottomSheet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.github.mohammadjoshaghani.composescreen.app.ProvideLayoutDirection
import com.github.mohammadjoshaghani.composescreen.base.BaseHandler
import com.github.mohammadjoshaghani.composescreen.base.BaseViewModel
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewEvent
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewSideEffect
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewState
import com.github.mohammadjoshaghani.composescreen.base.navigation.Navigator
import com.github.mohammadjoshaghani.composescreen.compose.bottomSheet.IBottomSheet.Companion.stack
import com.github.mohammadjoshaghani.composescreen.compose.toast.UIToastNotification
import com.github.mohammadjoshaghani.composescreen.extension.noRippleClickable
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseBottomSheet<State : ViewState<Event>, Event : ViewEvent, Effect : ViewSideEffect, VM : BaseViewModel<Event, State, Effect>> :
    IBottomSheet {

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

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun ShowBottomSheet() {
        LaunchedEffect(viewModel) {
            viewModel.effect.collect { handler.handleEffects(it, viewModel) }
        }
        ProvideLayoutDirection {
            ModalBottomSheet(
                onDismissRequest = { dismiss() },
                sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
            ) {
                Box(
                    modifier = Modifier
                        .noRippleClickable {
                            dismiss()
                        }
                ) {
                    Column(content = { ComposeView(state = viewModel.viewState.value) })
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