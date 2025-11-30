package com.example.kmpcomposescreen.textFieldTheme

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.kmpcomposescreen.textFieldTheme.utilities.TypeInputTextField
import com.example.kmpcomposescreen.textFieldTheme.utilities.getKeyboardOption
import com.example.kmpcomposescreen.textFieldTheme.utilities.getVisualTransformation
import com.example.kmpcomposescreen.textFieldTheme.utilities.passwordVisibilityBox
import com.example.kmpcomposescreen.textFieldTheme.utilities.processInput

@Composable
fun UITextField(
    text: String,
    label: String? = null,
    placeholder: String? = null,
    modifier: Modifier = Modifier,
    inputType: TypeInputTextField = TypeInputTextField.TEXT,
    singleLine: Boolean = true,
    keyboardActions: KeyboardActions = KeyboardActions(),
    enabled: Boolean = true,
    readOnly: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isErrorBorder: Boolean = false,
    maxLength: Int = 500,
    colorPlaceHolderText: Color = MaterialTheme.colorScheme.onSecondary,
    imeAction: ImeAction = ImeAction.Default,
    shape: Shape = MaterialTheme.shapes.medium,
    setFocus: ((Boolean) -> Unit)? = null,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = MaterialTheme.colorScheme.primary,
        unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceTint,
    ),
    onChangeValue: (String) -> Unit,
) {
    val passwordVisibility = remember { mutableStateOf(false) }


    OutlinedTextField(
        value = text,
        onValueChange = { newValue ->
            if (newValue.length <= maxLength) {
                val newText = processInput(newValue, inputType)
                onChangeValue.invoke(newText)
            }
        },
        modifier = modifier
            .onFocusChanged { focusState ->
                setFocus?.invoke(focusState.isFocused)
            }
            .padding(0.dp)
            .fillMaxWidth(),
        textStyle = LocalTextStyle.current.copy(
            color = if (enabled) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSecondary,
            fontWeight = FontWeight.Medium,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize
        ),
        singleLine = singleLine,
        placeholder = placeholder?.let {
            {
                Text(
                    text = it,
                    color = colorPlaceHolderText,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        },
        label = label?.let {
            {
                Text(
                    text = it,
                    color = colorPlaceHolderText,
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        },
        colors = colors,
        shape = shape,
        keyboardOptions = getKeyboardOption(inputType, imeAction),
        keyboardActions = keyboardActions,
        enabled = enabled,
        readOnly = readOnly,
        isError = isErrorBorder,
        visualTransformation = getVisualTransformation(inputType, text, passwordVisibility.value),
        leadingIcon = leadingIcon,
        trailingIcon = if (inputType == TypeInputTextField.PASSWORD) passwordVisibilityBox(
            passwordVisibility
        ) else trailingIcon,
    )
}

