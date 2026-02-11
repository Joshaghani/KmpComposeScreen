package com.example.kmpcomposescreen.screen.content.main.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.material.icons.rounded.Settings
import com.github.mohammadjoshaghani.composescreen.component.button.IconButton.IconButtonModel
import com.github.mohammadjoshaghani.composescreen.component.image.IconSourceType
import com.github.mohammadjoshaghani.composescreen.dialog.alertDialog.UIAlertDialog
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig
import org.koin.mp.KoinPlatform.getKoin

fun iconsActionTopBar(
    icons: IconButtonModel? = null,
) = listOfNotNull(
    icons,
    IconButtonModel(
        icon = IconSourceType.IconVector(Icons.Rounded.Settings),
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
    IconButtonModel(
        tint = ApplicationConfig.color.onBackground,
        icon =
            IconSourceType.IconVector(
                Icons.Rounded.LightMode
            ),
        onClick = {
        }
    )
)