package com.example.kmpcomposescreen.screen.content.categories.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kmpcomposescreen.textFieldTheme.UITextFieldValueType
import com.github.mohammadjoshaghani.composescreen.component.UISpacer
import com.github.mohammadjoshaghani.composescreen.component.button.UIPrimaryButton
import com.github.mohammadjoshaghani.composescreen.dialog.base.BaseSimpleDialog

class DialogAddCategory(val addCategory: (String) -> Unit) : BaseSimpleDialog() {

    @Composable
    override fun ComposeView() {
        Column(
            Modifier.Companion
                .padding(16.dp)
                .width(500.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Companion.CenterHorizontally
        ) {

            Text("ثبت دسته بندی")

            UISpacer()

            UIPrimaryButton("ثبت") {
                dismiss()
            }
        }
    }

}