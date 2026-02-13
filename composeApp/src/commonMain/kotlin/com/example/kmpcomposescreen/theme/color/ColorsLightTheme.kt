package com.example.kmpcomposescreen.theme.color

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val LightiOSColors: ColorScheme = lightColorScheme(
    primary = iOSRed,
    onPrimary = Color.White,
    primaryContainer = iOSRed.copy(alpha = 0.15f),
    onPrimaryContainer = iOSBlueDark,

    secondary = iOSBlue,
    onSecondary = Color.White,
    secondaryContainer = iOSBlue.copy(alpha = 0.15f),
    onSecondaryContainer = iOSBlue,

    tertiary = iOSSecondary,
    onTertiary = Color.White,
    tertiaryContainer = iOSSecondary.copy(alpha = 0.15f),
    onTertiaryContainer = iOSSecondary,

    error = iOSOrange,
    onError = Color.White,
    errorContainer = iOSOrange.copy(alpha = 0.15f),
    onErrorContainer = iOSOrange,

    background = iOSBackgroundLight,
    onBackground = Color.Black,

    surface = Color.White,
    onSurface = Color.Black,

    surfaceVariant = iOSGray,
    onSurfaceVariant = iOSGrayDark,

    outline = Color(0xFF8E8E93), // خاکستری مرزی iOS
    outlineVariant = Color(0xFFE2E2E7),

    inverseSurface = Color.Black,
    inverseOnSurface = Color.White,
    inversePrimary = iOSBlueDark,

    scrim = Color.Black
)

