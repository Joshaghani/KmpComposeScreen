package com.example.kmpcomposescreen.textFieldTheme.utilities

var maxWeightPackage = 6f
var maxWeightEnvelope = 2f

fun filterInput(input: String, isEnvelope: Boolean): String? {

    if (input.isEmpty()) {
        return input
    }

    val regex = if (isEnvelope) {
        """^([0-$maxWeightEnvelope](\.\d?)?|$maxWeightEnvelope(\.0?)?)$""".toRegex()
    } else {
        """^([0-$maxWeightPackage](\.\d?)?|$maxWeightPackage(\.0?)?)$""".toRegex()
    }

    return if (regex.matches(input)) input else null
}