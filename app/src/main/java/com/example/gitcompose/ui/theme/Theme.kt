package com.example.gitcompose.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200,
    background = Color.White,

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

private val DarkColorPalette = lightColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun GitComposeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = when {
        // Material You colors for Android 12+
        Build.VERSION.SDK_INT >= 31 -> {
            val mainDark700 = colorResource(android.R.color.system_accent1_700)
            val secondary200 = colorResource(android.R.color.system_accent2_200)
            when {
                darkTheme -> darkColors(
                    primary = colorResource(android.R.color.system_neutral2_500),
                    primaryVariant = mainDark700,
                    secondary = secondary200,
                    background = colorResource(android.R.color.system_accent2_900),
                    surface = colorResource(android.R.color.system_accent3_900),
                )
                else -> lightColors(
                    primary = colorResource(android.R.color.system_accent1_500),
                    primaryVariant = mainDark700,
                    secondary = secondary200,
                    background = colorResource(android.R.color.system_neutral1_10),
                    surface = colorResource(android.R.color.system_accent2_50),
                )
            }
        }
        darkTheme -> DarkColorPalette
        else -> LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}