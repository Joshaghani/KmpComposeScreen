package com.example.kmpcomposescreen.textFieldTheme.utilities

import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

fun getVisualTransformation(inputType: TypeInputTextField, value: String, passwordVisible: Boolean): VisualTransformation {
    return when {
        inputType == TypeInputTextField.NUMBER && value.isNotEmpty() -> VisualTransformation.None
        inputType == TypeInputTextField.CARD_BACK -> VisualTransformation {
            formatOtherCardNumbers(
                it
            )
        }
        inputType == TypeInputTextField.PASSWORD && !passwordVisible -> PasswordVisualTransformation()
        inputType == TypeInputTextField.SHEBA -> VisualTransformation {
            formatShebaNumber(it)
        }
        else -> VisualTransformation.None
    }
}
