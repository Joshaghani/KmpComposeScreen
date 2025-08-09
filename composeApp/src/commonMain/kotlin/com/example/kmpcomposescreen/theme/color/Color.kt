package com.example.kmpcomposescreen.theme.color

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig

fun getApplicationColorScheme(themeApplicationState: Boolean): ColorScheme {

    if (themeApplicationState) {
        val darkColors = ColorsDarkTheme()
        return darkColorScheme(
            primary = darkColors.primary,
            onPrimary = darkColors.onPrimary,
            secondary = darkColors.secondary,
            surface = darkColors.surface,
            onSecondary = darkColors.onSecondary,
            primaryContainer = darkColors.primaryContainer,
            onBackground = darkColors.onBackground,
            background = darkColors.background,
            surfaceVariant = darkColors.surfaceVariant,
            onSurfaceVariant = darkColors.onSurfaceVariant,
            onTertiary = darkColors.onTertiary,
            onPrimaryContainer = darkColors.onPrimaryContainer,
            tertiary = darkColors.tertiary,
            surfaceTint = darkColors.surfaceTint,
            error = darkColors.error,
            onTertiaryContainer = darkColors.onTertiaryContainer
        )
    } else {
        val lightColors = ColorsLightTheme()
        return lightColorScheme(
            primary = lightColors.primary,
            onPrimary = lightColors.onPrimary,
            secondary = lightColors.secondary,
            surface = lightColors.surface,
            onSecondary = lightColors.onSecondary,
            primaryContainer = lightColors.primaryContainer,
            onBackground = lightColors.onBackground,
            background = lightColors.background,
            surfaceVariant = lightColors.surfaceVariant,
            onSurfaceVariant = lightColors.onSurfaceVariant,
            onTertiary = lightColors.onTertiary,
            onPrimaryContainer = lightColors.onPrimaryContainer,
            tertiary = lightColors.tertiary,
            outlineVariant = lightColors.outlineVariant,
            surfaceTint = lightColors.surfaceTint,
            error = lightColors.error,
            onTertiaryContainer = lightColors.onTertiaryContainer,
        )
    }

}

val colorTheme by lazy { ApplicationConfig.config.color }


