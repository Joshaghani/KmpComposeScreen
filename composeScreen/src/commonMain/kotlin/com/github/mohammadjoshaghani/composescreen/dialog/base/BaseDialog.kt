package com.github.mohammadjoshaghani.composescreen.dialog.base

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.github.mohammadjoshaghani.composescreen.app.ProvideLayoutDirection
import com.github.mohammadjoshaghani.composescreen.app.RenderNotifications
import com.github.mohammadjoshaghani.composescreen.component.UIBorderCard
import com.github.mohammadjoshaghani.composescreen.extension.noRippleClickable
import com.github.mohammadjoshaghani.composescreen.screen.BaseHandler
import com.github.mohammadjoshaghani.composescreen.screen.BaseViewModel
import com.github.mohammadjoshaghani.composescreen.screen.contract.ViewEvent
import com.github.mohammadjoshaghani.composescreen.screen.contract.ViewSideEffect
import com.github.mohammadjoshaghani.composescreen.screen.contract.ViewState
import com.github.mohammadjoshaghani.composescreen.screen.toast.ToastCreator
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig
import com.github.mohammadjoshaghani.composescreen.dialog.base.IBaseDialog.Companion.stack
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BaseDialog<State : ViewState<Event>, Event : ViewEvent, Effect : ViewSideEffect, VM : BaseViewModel<State, Event, Effect>>() :
    IBaseDialog, CoroutineScope {

    abstract val viewModel: VM
    abstract val handler: BaseHandler<Event, Effect, VM>

    var onEventSent: (Event) -> Unit = { viewModel.handleEvents(it) }

    private val isShowDialogFlow = MutableStateFlow(true)

    private var setCanceledOnTouchOutside: Boolean = true

    private var job: Job = Job()

    open var modifier: Modifier = Modifier
    open var backgroundColor: Color = ApplicationConfig.color.surface
    open var borderColor: Color = ApplicationConfig.color.outlineVariant
    open var elevation: Dp = 0.dp
    open var paddingValue: Dp = 16.dp
    open var borderWidth: Dp = 1.dp
    open var shape: CornerBasedShape = RoundedCornerShape(16.dp)
    open var verticalArrangement: Arrangement.Vertical = Arrangement.Center
    open var horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally


    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    fun setCanceledOnTouchOutside(value: Boolean) = apply {
        setCanceledOnTouchOutside = value
    }

    override fun show() {
        stack.add(this)
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
                    modifier = Modifier
                        .fillMaxSize()
                        .noRippleClickable {
                            if (setCanceledOnTouchOutside) {
                                dismiss()
                            }
                        },
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {

                        Column(modifier = Modifier.noRippleClickable {}) {
                            when {
                                viewModel.state.value.isLoading -> ShowLoadingIndicator()
                                viewModel.state.value.errorScreen != null -> {
                                    ShowErrorScreen()
                                }

                                else -> UIBorderCard(
                                    modifier = modifier,
                                    backgroundColor = backgroundColor,
                                    borderColor = borderColor,
                                    elevation = elevation,
                                    paddingValue = paddingValue,
                                    borderWidth = borderWidth,
                                    shape = shape,
                                    verticalArrangement = verticalArrangement,
                                    horizontalAlignment = horizontalAlignment,
                                ) {
                                    ComposeView(viewModel.state.value)
                                }
                            }

                        }

                        val toastMessage = viewModel.state.value.toastMessage
                        LaunchedEffect(toastMessage) {
                            if (toastMessage != null) {
                                ToastCreator.showToast(toastMessage)
                                viewModel.state.value.toastMessage = null
                            }
                        }

                    }
                    RenderNotifications()
                }
            }
        }

    }


    @Composable
    private fun ShowLoadingIndicator() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            ApplicationConfig.loadingScreen?.let { screen ->
                screen()
            } ?: run {
                CircularProgressIndicator()
            }
        }
    }

    @Composable
    private fun ShowErrorScreen() {
        val errorScreen = viewModel.state.value.errorScreen!!
        ApplicationConfig.errorScreen(errorScreen.message) {
            viewModel.handleEvents(errorScreen.event)
        }
    }

    @Composable
    abstract fun ComposeView(state: State)

    fun dismiss() {
        stack.remove(this)
        isShowDialogFlow.value = false
    }

    fun onDismissRequest(action: () -> Unit) = apply {
        launch {
            isShowDialogFlow.collect {
                if (!it) action()
            }
        }
    }

    fun cancelCoroutine() {
        job.cancel()
    }
}