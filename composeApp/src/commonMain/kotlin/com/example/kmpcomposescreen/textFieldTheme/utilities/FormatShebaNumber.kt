package com.example.kmpcomposescreen.textFieldTheme.utilities

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText

fun formatShebaNumber(text: AnnotatedString): TransformedText {

    if (text.text.isEmpty()) {
        return TransformedText(AnnotatedString(""), OffsetMapping.Identity)
    }

    val trimmed = if (text.text.length >= 24) text.text.substring(0..23) else text.text

    var out = ""

    for (i in trimmed.indices) {
        out += trimmed[i]
        if (i % 4 == 3 && i != 23) out += " "
    }


    val shebaOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 3) return offset     // 1234
            if (offset <= 7) return offset + 1  // 1234 567
            if (offset <= 11) return offset + 2 // ...
            if (offset <= 15) return offset + 3 // ...
            if (offset <= 19) return offset + 4 // ...
            if (offset <= 24) return offset + 5 // ...
            return 29
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 4) return offset     // 1234
            if (offset <= 9) return offset - 1  // 1234 5678
            if (offset <= 14) return offset - 2 // ...
            if (offset <= 19) return offset - 3 // ...
            if (offset <= 24) return offset - 4 // ...
            if (offset <= 29) return offset - 5 // ...
            return 24
        }
    }

    return TransformedText(AnnotatedString(out), shebaOffsetTranslator)
}