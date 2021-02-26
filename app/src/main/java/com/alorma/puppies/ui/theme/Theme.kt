package com.alorma.puppies.ui.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

val LightColorPalette = lightColors(
    primary = canary,
    primaryVariant = canary_d_1,
    onPrimary = black,
    secondary = bamboo,
    onSecondary = white,
    background = gray_l_4,
    surface = white,
    error = tomato
)

val DarkColorPalette = darkColors(
    primary = canary_l_3,
    primaryVariant = canary,
    onPrimary = black,
    secondary = bamboo_l_3,
    onSecondary = black,
    background = gray_v,
    surface = black,
    error = tomato_l_1
)

@Composable
fun PuppiesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable (MutableState<Boolean>) -> Unit
) {
    val darkThemeState = remember { mutableStateOf(darkTheme) }

    val colors = if (darkThemeState.value) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = { content(darkThemeState) }
    )
}