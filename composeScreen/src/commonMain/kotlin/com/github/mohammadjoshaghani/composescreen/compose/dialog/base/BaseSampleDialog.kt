package com.github.mohammadjoshaghani.composescreen.compose.dialog.base

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.github.mohammadjoshaghani.composescreen.app.ProvideLayoutDirection
import com.github.mohammadjoshaghani.composescreen.base.navigation.Navigator
import com.github.mohammadjoshaghani.composescreen.compose.dialog.base.IBaseDialog.Companion.stack
import com.github.mohammadjoshaghani.composescreen.compose.toast.UIToastNotification
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseSampleDialog : IBaseDialog {

    private val isShowDialogFlow = MutableStateFlow(true)

    private var setCanceledOnTouchOutside: Boolean = true
    private var modifier: Modifier = Modifier.Companion
    private var shape: Shape = RoundedCornerShape(0.dp)

    private var result: List<Any?>? = null

    fun setCanceledOnTouchOutside(value: Boolean) = apply {
        setCanceledOnTouchOutside = value
    }

    override fun show(
        modifier: Modifier,
        shape: Shape,
    ) {
        this.modifier = modifier
        this.shape = shape
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
                    Column(
                        modifier = Modifier.Companion.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Companion.CenterHorizontally
                    ) {
                        Card(
                            modifier = modifier,
                            shape = shape,
                            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
                        ) {
                            Column { ComposeView() }
                        }
                    }

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


    protected fun setResult(vararg data: Any?) {
        result = data.toList()
    }

    fun onDismissRequest(action: (List<Any?>?) -> Unit) = apply {
        Navigator.state.current.value?.viewModel?.launchOnScope {
            isShowDialogFlow.collect {
                if (!it) action(result)
            }
        }
    }

}