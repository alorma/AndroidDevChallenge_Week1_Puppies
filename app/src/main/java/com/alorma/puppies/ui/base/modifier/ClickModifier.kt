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
package com.alorma.puppies.ui.base.modifier

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color

fun Modifier.onSurfaceClick(
    alpha: Float = 0.20f,
    onClick: () -> Unit
) = composed {
    coloredClick(color = MaterialTheme.colors.onSurface, alpha = alpha, onClick = onClick)
}

fun Modifier.primaryClick(
    alpha: Float = 0.20f,
    onClick: () -> Unit
) = composed {
    coloredClick(color = MaterialTheme.colors.primary, alpha = alpha, onClick = onClick)
}

fun Modifier.secondaryClick(
    alpha: Float = 0.20f,
    onClick: () -> Unit
) = composed {
    coloredClick(color = MaterialTheme.colors.secondary, alpha = alpha, onClick = onClick)
}

fun Modifier.errorClick(
    alpha: Float = 0.20f,
    onClick: () -> Unit
) = composed {
    coloredClick(color = MaterialTheme.colors.error, alpha = alpha, onClick = onClick)
}

fun Modifier.coloredClick(
    color: Color,
    alpha: Float = 0.20f,
    onClick: () -> Unit
) = composed {
    clickable(
        indication = rememberRipple(color = color.copy(alpha = alpha)),
        interactionSource = remember { MutableInteractionSource() },
        onClick = onClick
    )
}
