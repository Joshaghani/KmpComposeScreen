package com.example.kmpcomposescreen.textFieldTheme.utilities

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType

fun getKeyboardOption(
    intputType: TypeInputTextField,
    imeAction: ImeAction,
): KeyboardOptions {
    return when (intputType) {
        TypeInputTextField.TEXT -> KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            autoCorrectEnabled = true,
            keyboardType = KeyboardType.Text,
            imeAction = imeAction
        )

        TypeInputTextField.PASSWORD -> KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            autoCorrectEnabled = true,
            keyboardType = KeyboardType.Password,
            imeAction = imeAction
        )

        TypeInputTextField.NUMBER,
        TypeInputTextField.NUMBER_WEIGHT,
        TypeInputTextField.NUMBER_WEIGHT_ENVELOPE,
        TypeInputTextField.CARD_BACK,
        TypeInputTextField.PHONE_NUMBER,
            -> KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            autoCorrectEnabled = true,
            keyboardType = KeyboardType.Number,
            imeAction = imeAction
        )

        TypeInputTextField.EMAIL -> KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            autoCorrectEnabled = true,
            keyboardType = KeyboardType.Email,
            imeAction = imeAction
        )


        TypeInputTextField.SHEBA
            -> KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            autoCorrectEnabled = true,
            keyboardType = KeyboardType.Number,
            imeAction = imeAction
        )

    }
}