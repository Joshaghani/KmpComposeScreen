package com.example.kmpcomposescreen.textFieldTheme.utilities

import androidx.compose.foundation.layout.size
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun passwordVisibilityBox(
    passwordVisibility: MutableState<Boolean>
): @Composable (() -> Unit) {
    return {
        TextButton(
            onClick = { passwordVisibility.value = !passwordVisibility.value },
            Modifier.size(56.dp, 56.dp)
        ) {
//            Icon(
//                painter = if (passwordVisibility.value) {
//                    painterResource(Res.drawable.ic_visibility_on)
//                } else {
//                    painterResource(Res.drawable.ic_visibility_off)
//                }, tint = MaterialTheme.colorScheme.primary, contentDescription = null
//            )
        }
    }
}
