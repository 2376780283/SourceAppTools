package zzh.bin.sourceapptools.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
                        primary = green_primaryDark,
                    onPrimary = green_onPrimaryDark,
                    primaryContainer = green_primaryContainerDark,
                    onPrimaryContainer = green_onPrimaryContainerDark,
                    secondary = green_secondaryDark,
                    onSecondary = green_onSecondaryDark,
                    secondaryContainer = green_secondaryContainerDark,
                    onSecondaryContainer = green_onSecondaryContainerDark,
                    tertiary = green_tertiaryDark,
                    onTertiary = green_onTertiaryDark,
                    tertiaryContainer = green_tertiaryContainerDark,
                    onTertiaryContainer = green_onTertiaryContainerDark,
                    error = green_errorDark,
                    onError = green_onErrorDark,
                    errorContainer = green_errorContainerDark,
                    onErrorContainer = green_onErrorContainerDark,
                    background = green_backgroundDark,
                    onBackground = green_onBackgroundDark,
                    surface = green_surfaceDark,
                    onSurface = green_onSurfaceDark,
                    surfaceVariant = green_surfaceVariantDark,
                    onSurfaceVariant = green_onSurfaceVariantDark,
                    outline = green_outlineDark,
                    outlineVariant = green_outlineVariantDark,
                    scrim = green_scrimDark,
                    inverseSurface = green_inverseSurfaceDark,
                    inverseOnSurface = green_inverseOnSurfaceDark,
                    inversePrimary = green_inversePrimaryDark,
                    surfaceDim = green_surfaceDimDark,
                    surfaceBright = green_surfaceBrightDark,
                    surfaceContainerLowest = green_surfaceContainerLowestDark,
                    surfaceContainerLow = green_surfaceContainerLowDark,
                    surfaceContainer = green_surfaceContainerDark,
                    surfaceContainerHigh = green_surfaceContainerHighDark,
                    surfaceContainerHighest = green_surfaceContainerHighestDark,
)

private val LightColorScheme = lightColorScheme(
    primary = green_primaryLight,
                    onPrimary = green_onPrimaryLight,
                    primaryContainer = green_primaryContainerLight,
                    onPrimaryContainer = green_onPrimaryContainerLight,
                    secondary = green_secondaryLight,
                    onSecondary = green_onSecondaryLight,
                    secondaryContainer = green_secondaryContainerLight,
                    onSecondaryContainer = green_onSecondaryContainerLight,
                    tertiary = green_tertiaryLight,
                    onTertiary = green_onTertiaryLight,
                    tertiaryContainer = green_tertiaryContainerLight,
                    onTertiaryContainer = green_onTertiaryContainerLight,
                    error = green_errorLight,
                    onError = green_onErrorLight,
                    errorContainer = green_errorContainerLight,
                    onErrorContainer = green_onErrorContainerLight,
                    background = green_backgroundLight,
                    onBackground = green_onBackgroundLight,
                    surface = green_surfaceLight,
                    onSurface = green_onSurfaceLight,
                    surfaceVariant = green_surfaceVariantLight,
                    onSurfaceVariant = green_onSurfaceVariantLight,
                    outline = green_outlineLight,
                    outlineVariant = green_outlineVariantLight,
                    scrim = green_scrimLight,
                    inverseSurface = green_inverseSurfaceLight,
                    inverseOnSurface = green_inverseOnSurfaceLight,
                    inversePrimary = green_inversePrimaryLight,
                    surfaceDim = green_surfaceDimLight,
                    surfaceBright = green_surfaceBrightLight,
                    surfaceContainerLowest = green_surfaceContainerLowestLight,
                    surfaceContainerLow = green_surfaceContainerLowLight,
                    surfaceContainer = green_surfaceContainerLight,
                    surfaceContainerHigh = green_surfaceContainerHighLight,
                    surfaceContainerHighest = green_surfaceContainerHighestLight,

)

@Composable
fun SourceAppToolsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor -> {
            val context = LocalView.current.context
            // MaterialTheme.colorScheme.apply { ... } // This would be for dynamic colors
            if (darkTheme) DarkColorScheme else LightColorScheme
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}