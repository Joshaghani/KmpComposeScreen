package com.github.mohammadjoshaghani.composescreen.dialog.alertDialog

import androidx.compose.runtime.Composable

interface IAlertDialog {
    @Composable
    fun ShowDialog()

    fun dismiss()
}