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
package com.alorma.puppies.ui.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.alorma.puppies.data.PuppyProvider
import com.alorma.puppies.ui.base.modifier.primaryClick
import com.alorma.puppies.ui.model.PuppyItemModel
import com.alorma.puppies.ui.theme.PuppiesTheme

@Composable
fun PuppyItem(
    puppy: PuppyItemModel,
    onClick: () -> Unit,
) {
    val avatarHeight = 180.dp
    val avatarWidth = 120.dp
    Box(
        modifier = Modifier.padding(
            start = 8.dp,
            top = 2.dp,
            end = 8.dp
        )
    ) {
        PuppyItemCard(avatarHeight, avatarWidth, puppy, onClick = onClick)
        PuppyAvatar(
            modifier = Modifier
                .height(avatarHeight)
                .width(avatarWidth)
                .clip(shape = MaterialTheme.shapes.medium),
            puppy = puppy
        )
    }
}

@Composable
private fun PuppyItemCard(
    avatarHeight: Dp,
    avatarWidth: Dp,
    puppy: PuppyItemModel,
    onClick: () -> Unit,
) {
    val verticalDistanceFromAvatar = 24.dp
    Card(
        modifier = Modifier
            .padding(
                top = verticalDistanceFromAvatar,
                start = avatarWidth / 2,
                end = 8.dp,
                bottom = verticalDistanceFromAvatar,
            )
            .height(avatarHeight - verticalDistanceFromAvatar * 2)
            .fillMaxWidth()
            .primaryClick { onClick() }
            .clip(MaterialTheme.shapes.medium)
            .clipToBounds(),
        shape = MaterialTheme.shapes.medium,
        elevation = 4.dp,
    ) {
        Column(
            modifier = Modifier.padding(
                top = 2.dp,
                start = avatarWidth / 2 + 8.dp,
                end = 8.dp,
                bottom = 8.dp
            )
        ) {
            Column {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = puppy.name,
                    style = MaterialTheme.typography.h5,
                )
                Text(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .fillMaxWidth(),
                    text = "Breed:",
                    style = MaterialTheme.typography.body1,
                )
                Text(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .fillMaxWidth(),
                    text = "Age:",
                    style = MaterialTheme.typography.body1,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewPuppy() {
    PuppiesTheme {
        val puppy = PuppyProvider.randomPuppy()
        PuppyItem(puppy = puppy) { }
    }
}
