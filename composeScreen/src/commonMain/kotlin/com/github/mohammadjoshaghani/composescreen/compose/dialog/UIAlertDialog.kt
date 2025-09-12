package com.github.mohammadjoshaghani.composescreen.compose.dialog

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.github.mohammadjoshaghani.composescreen.base.navigation.Navigator
import com.github.mohammadjoshaghani.composescreen.compose.dialog.compose.CustomUIAlertDialog
import com.github.mohammadjoshaghani.composescreen.compose.dialog.compose.SampleUiAlertDialog
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig
import kotlinx.coroutines.flow.MutableStateFlow

class UIAlertDialog(
    internal var title: String? = null,
    internal var message: String? = null,
    internal var buttonActionText: String? = null,
    internal var buttonActionBlock: (() -> Unit)? = null,
    internal var buttonCancelTitle: String? = null,
    internal var buttonCancelBlock: (() -> Unit)? = null,
    internal var setCanceledOnTouchOutside: Boolean = true,
    internal var primaryButtonContainerColor: Color = ApplicationConfig.config.color.primary,
    internal var primaryButtonContentColor: Color = ApplicationConfig.config.color.onPrimary,
    internal var cancelButtonContentColor: Color = ApplicationConfig.config.color.onBackground,
) : IAlertDialog {

    private val isShowDialogFlow = MutableStateFlow(true)
    private var sampleDialogContent: (@Composable ColumnScope.(UIAlertDialog) -> Unit)? = null

    internal lateinit var modifierCustomUi: Modifier
    internal var shapeCustomUi: Shape? = null

    fun show() {
        showSampleDialogState.value = this
    }


    fun onDismissRequest(action: () -> Unit) = apply {
        Navigator.state.current.value?.viewModel?.launchOnScope {
            isShowDialogFlow.collect {
                if (!it) action()
            }
        }
    }

    @Composable
    fun setCustomContent(
        modifier: Modifier = Modifier,
        shape: Shape? = null,
        content: @Composable ColumnScope.(UIAlertDialog) -> Unit,
    ) = apply {
        this.sampleDialogContent = content
        this.modifierCustomUi = modifier
        this.shapeCustomUi = shape
    }

    fun setTitle(title: String) = apply { this.title = title }
    fun setMessage(message: String) = apply { this.message = message }

    fun setPrimaryContentButtonColor(color: Color) =
        apply { this.primaryButtonContentColor = color }

    fun setPrimaryButtonContainerColor(color: Color) =
        apply { this.primaryButtonContainerColor = color }

    fun setCancelButtonContentColor(color: Color) =
        apply { this.cancelButtonContentColor = color }

    fun setCanceledOnTouchOutside(value: Boolean) = apply {
        this.setCanceledOnTouchOutside = value
    }

    fun setButtonAction(text: String, block: (() -> Unit)? = null) = apply {
        this.buttonActionText = text
        this.buttonActionBlock = block
    }

    fun setButtonCancelTitle(text: String, block: (() -> Unit)? = null) = apply {
        this.buttonCancelTitle = text
        this.buttonCancelBlock = block
    }

    @Composable
    override fun ShowDialog() {
        if (!isShow()) return

        Dialog(
            properties = DialogProperties(
                dismissOnClickOutside = setCanceledOnTouchOutside,
                usePlatformDefaultWidth = false
            ),
            onDismissRequest = { dismiss() }
        ) {
            sampleDialogContent?.let {
                CustomUIAlertDialog(modifierCustomUi, shapeCustomUi, it)
            } ?: SampleUiAlertDialog()
        }
    }


    override fun dismiss() {
        showSampleDialogState.value = null
        isShowDialogFlow.value = false
    }

    companion object {

        private val showSampleDialogState: MutableState<IAlertDialog?> by lazy {
            mutableStateOf(null)
        }

        fun isShow(): Boolean = showSampleDialogState.value != null
        fun getDialog(): IAlertDialog? = showSampleDialogState.value
    }
}

