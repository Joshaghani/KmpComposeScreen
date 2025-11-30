package com.example.kmpcomposescreen.textFieldTheme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.kmpcomposescreen.textFieldTheme.utilities.TypeInputTextField
import com.github.mohammadjoshaghani.composescreen.compose.component.UISpacer
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun UITextFieldTitle(
    title: String,
    text: String,
    warningTitle: String? = null,
    modifier: Modifier = Modifier,
    placeholder: String? = null,
    label: String? = null,
    topPadding: Boolean = true,
    inputType: TypeInputTextField = TypeInputTextField.TEXT,
    singleLine: Boolean = true,
    keyboardActions: KeyboardActions = KeyboardActions(),
    enabled: Boolean = true,
    readOnly: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isErrorBorder: Boolean = false,
    showErrorText: Boolean = false,
    textErrorText: String = "",
    icon: DrawableResource? = null,
    textErrorColor: Color = MaterialTheme.colorScheme.error,
    maxLength: Int = 500,
    colorPlaceHolderText: Color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f),
    shape: Shape = MaterialTheme.shapes.medium,
    imeAction: ImeAction = ImeAction.Default,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = MaterialTheme.colorScheme.primary,
        unfocusedBorderColor = MaterialTheme.colorScheme.background,
        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceTint,
    ),
    onChangeValue: (String) -> Unit,
) {

    Column {

        if (topPadding) {
            UISpacer(16)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            icon?.let {
                Icon(
                    painter = painterResource(it),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary
                )
                UISpacer(8)
            }
            Text(
                modifier = Modifier.padding(horizontal = 2.dp),
                text = title,
                color = MaterialTheme.colorScheme.secondary
            )
            warningTitle?.let {
                Text(
                    modifier = Modifier.padding(horizontal = 6.dp),
                    text = warningTitle,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        UITextField(
            text = text,
            label = label,
            placeholder,
            colorPlaceHolderText = colorPlaceHolderText,
            modifier = modifier,
            inputType = inputType,
            singleLine = singleLine,
            keyboardActions = keyboardActions,
            enabled = enabled,
            readOnly = readOnly,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            isErrorBorder = isErrorBorder,
            maxLength = maxLength,
            imeAction = imeAction,
            colors = colors,
            shape = shape,
            onChangeValue = onChangeValue
        )

        if (showErrorText) {
            UISpacer(8)
            Row(
                modifier = Modifier.fillMaxWidth(0.95f), horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = textErrorText,
                    color = textErrorColor,
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
    }

}