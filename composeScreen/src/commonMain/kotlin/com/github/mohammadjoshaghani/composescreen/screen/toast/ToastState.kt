package com.github.mohammadjoshaghani.composescreen.screen.toast

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Message
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material.icons.rounded.Event
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig
import org.jetbrains.compose.resources.DrawableResource

enum class ToastState(val icon: ImageVector?, val vector: DrawableResource?, val textColor: Color) {
    ERROR(Icons.Rounded.Error, null, ApplicationConfig.config.color.error),
    WARNING(Icons.Rounded.Warning, null, ApplicationConfig.config.color.errorContainer),
    SUCCESS(Icons.Rounded.Event, null, ApplicationConfig.config.color.primary),
    MESSAGE(Icons.AutoMirrored.Rounded.Message, null, ApplicationConfig.config.color.onSecondary)
}