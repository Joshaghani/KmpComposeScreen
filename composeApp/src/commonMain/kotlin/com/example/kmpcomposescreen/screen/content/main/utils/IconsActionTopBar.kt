package com.example.kmpcomposescreen.screen.content.main.utils

import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.Color
import com.github.mohammadjoshaghani.composescreen.component.button.IconButton.ButtonModel
import com.github.mohammadjoshaghani.composescreen.dialog.alertDialog.UIAlertDialog
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig

fun iconsActionTopBar(
    icons: ButtonModel? = null,
) = listOfNotNull(
    icons,
    ButtonModel(
        icon = Icons.Rounded.Settings,
        tint = Color.Unspecified,
        onClick = {
            UIAlertDialog()
                .setTitle("خروج از حساب کاربری")
                .setMessage("آیا میخواهید از حساب خود خارج شوید؟")
                .setButtonAction("خارج میشوم") {
                }
                .setButtonCancelTitle("انصراف")
                .setCancelButtonContentColor(Color.Unspecified)
                .show()
        }),
    ButtonModel(
        tint = Color.Unspecified,
        icon = Icons.Rounded.LightMode,
        onClick = {
        }
    )
)