package com.example.kmpcomposescreen.textFieldTheme.utilities


fun processInput(input: String, inputType: TypeInputTextField): String {
    return when (inputType) {
        TypeInputTextField.NUMBER -> {
//            if (input.isDigitsOnly()) {
//                input.convertPersianNumbersToEnglish()
//            } else {
            input
//            }
        }

        TypeInputTextField.NUMBER_WEIGHT,
        TypeInputTextField.NUMBER_WEIGHT_ENVELOPE,
            -> {
//            filterInput(
//                input,
//                inputType == TypeInputTextField.NUMBER_WEIGHT_ENVELOPE
//            )?.convertPersianNumbersToEnglish() ?:
            input
        }

        else -> input
    }
}