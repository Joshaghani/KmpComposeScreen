package com.github.mohammadjoshaghani.composescreen.compose.dialog.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

internal interface IBaseDialog {

    fun show(
        modifier: Modifier = Modifier.Companion,
        shape: Shape = androidx.compose.foundation.shape.RoundedCornerShape(0.dp),
        )

    @Composable
    fun ShowDialog()

    companion object {
        val stack = mutableStateListOf<IBaseDialog>()
    }
}