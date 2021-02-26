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
package com.alorma.puppies.ui.base.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alorma.puppies.R
import com.alorma.puppies.ui.base.modifier.primaryClick
import com.alorma.puppies.ui.theme.PuppiesTheme

@Composable
fun Chip(
    text: String,
    icon: Int? = null,
    selected: Boolean,
    onSelectedChanged: (Boolean) -> Unit
) {
    val color = if (selected) {
        MaterialTheme.colors.primary
    } else {
        MaterialTheme.colors.onBackground.copy(alpha = 0.20f)
            .compositeOver(MaterialTheme.colors.background)
    }

    val textColor = if (selected) {
        MaterialTheme.colors.onPrimary
    } else {
        MaterialTheme.colors.onBackground
    }

    Card(
        backgroundColor = color,
        shape = MaterialTheme.shapes.small,
    ) {
        Row(
            modifier = Modifier
                .primaryClick(onClick = { onSelectedChanged(!selected) })
                .padding(horizontal = 8.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {

            if (icon != null) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    tint = textColor,
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = text,
                style = MaterialTheme.typography.body2,
                color = textColor
            )
            if (icon != null) {
                Spacer(modifier = Modifier.requiredWidth(4.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewUnSelectedChip() {
    PuppiesTheme {
        Chip(
            text = "UnSelected",
            icon = R.drawable.ic_pawn,
            selected = false,
        ) { }
    }
}

@Preview(showBackground = true)
@Composable
fun previewSelectedChip() {
    PuppiesTheme {
        Chip(
            text = "Selected",
            selected = true,
        ) { }
    }
}
