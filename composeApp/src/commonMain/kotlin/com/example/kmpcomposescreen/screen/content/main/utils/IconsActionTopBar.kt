package com.example.kmpcomposescreen.screen.content.main.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.material.icons.rounded.Settings
import com.github.mohammadjoshaghani.composescreen.component.button.IconButton.ButtonModel
import com.github.mohammadjoshaghani.composescreen.dialog.alertDialog.UIAlertDialog
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig

fun iconsActionTopBar(
    icons: ButtonModel? = null,
) = listOfNotNull(
    icons,
    ButtonModel(
        icon = Icons.Rounded.Settings,
        tint = ApplicationConfig.color.onBackground,
        onClick = {
            UIAlertDialog()
                .setTitle("خروج از حساب کاربری")
                .setMessage("آیا میخواهید از حساب خود خارج شوید؟")
                .setButtonAction("خارج میشوم") {
                }
                .setButtonCancelTitle("انصراف")
                .setCancelButtonContentColor(ApplicationConfig.color.onBackground)
                .show()
        }),
    ButtonModel(
        tint = ApplicationConfig.color.onBackground,
        icon = Icons.Rounded.LightMode,
        onClick = {
        }
    )
)