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
package com.alorma.puppies.base

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.alorma.drawer_modules.ActionsModule
import com.alorma.drawer_modules.actions.SwitchAction

@Composable
fun DarkThemeModule(
    modifier: Modifier = Modifier,
    darkThemeState: MutableState<Boolean>
) {
    ActionsModule(
        modifier = modifier,
        icon = {
            val vector = if (darkThemeState.value) {
                Icons.Default.DarkMode
            } else {
                Icons.Default.LightMode
            }
            Icon(imageVector = vector, contentDescription = "Dark theme module icon")
        },
        title = "Dark mode"
    ) {
        SwitchAction(
            text = "Enabled",
            isChecked = darkThemeState.value,
            onChange = { darkThemeState.value = !darkThemeState.value }
        )
    }
}
