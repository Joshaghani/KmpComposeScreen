package com.example.kmpcomposescreen.theme.color

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig

fun getApplicationColorScheme(themeApplicationState: Boolean): ColorScheme {
    return if (themeApplicationState) {
        DarkiOSColors
    } else {
        LightiOSColors
    }
}



// 🎨 رنگ‌های iOS
val iOSBlue = Color(0xFF007AFF)
val iOSBlueDark = Color(0xFF0040DD) // آبی تیره‌تر
val iOSGray = Color(0xFFE5E5EA)
val iOSGrayDark = Color(0xFF1C1C1E)
val iOSBackgroundLight = Color(0xFFF2F2F7)
val iOSBackgroundDark = Color(0xFF121212)
val iOSSecondary = Color(0xFF34C759) // سبز معروف iOS
val iOSRed = Color(0xFFFF3B30) // قرمز هشدار
val iOSOrange = Color(0xFFFF9500)
val iOSYellow = Color(0xFFFFCC00)


