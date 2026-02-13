package com.example.kmpcomposescreen.theme.color

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color


val DarkiOSColors: ColorScheme = darkColorScheme(
    primary = iOSRed,
    onPrimary = Color.White,
    primaryContainer = iOSRed.copy(alpha = 0.25f),
    onPrimaryContainer = iOSBlueDark,

    secondary = iOSBlue,
    onSecondary = Color.Black,
    secondaryContainer = iOSBlue.copy(alpha = 0.25f),
    onSecondaryContainer = iOSBlue,

    tertiary = iOSSecondary,
    onTertiary = Color.Black,
    tertiaryContainer = iOSSecondary.copy(alpha = 0.25f),
    onTertiaryContainer = iOSSecondary,

    error = iOSOrange,
    onError = Color.Black,
    errorContainer = iOSOrange.copy(alpha = 0.25f),
    onErrorContainer = iOSOrange,

    background = iOSBackgroundDark,
    onBackground = Color.White,

    surface = iOSGrayDark,
    onSurface = Color.White,

    surfaceVariant = Color(0xFF2C2C2E),
    onSurfaceVariant = iOSGray,

    outline = Color(0xFF8E8E93),
    outlineVariant = Color(0xFF232326),

    inverseSurface = Color.White,
    inverseOnSurface = Color.Black,
    inversePrimary = iOSBlueDark,

    scrim = Color.Black
)