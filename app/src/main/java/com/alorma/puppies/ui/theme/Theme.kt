/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alorma.puppies.ui.theme

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
    secondary = soda,
    onSecondary = white,
    background = gray_l_4,
    surface = white,
    error = tomato
)

val DarkColorPalette = darkColors(
    primary = canary_l_3,
    primaryVariant = canary,
    onPrimary = black,
    secondary = soda_l_3,
    onSecondary = black,
    background = gray_v,
    surface = black,
    error = tomato_l_1
)

@Composable
fun PuppiesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    darkThemeState: MutableState<Boolean> = remember { mutableStateOf(darkTheme) },
    content: @Composable (MutableState<Boolean>) -> Unit
) {
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
