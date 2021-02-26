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
import androidx.compose.ui.text.font.FontWeight
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
    val avatarHeight = 140.dp
    val avatarWidth = 140.dp
    Box {
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
    val verticalDistanceFromAvatar = 12.dp
    Card(
        modifier = Modifier
            .padding(
                top = verticalDistanceFromAvatar,
                start = avatarWidth / 2,
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
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = puppy.name,
                fontWeight = FontWeight.Bold,
            )
            PuppyItemCardRowInfo("Breed: ${puppy.breed.name}")
            PuppyItemCardRowInfo("Age: ${puppy.age}")
        }
    }
}

@Composable
fun PuppyItemCardRowInfo(text: String) {
    Text(
        modifier = Modifier
            .padding(start = 4.dp)
            .fillMaxWidth(),
        text = text,
        fontWeight = FontWeight.Light,
        style = MaterialTheme.typography.body2,
    )
}

@Preview(showBackground = true)
@Composable
fun previewPuppy() {
    PuppiesTheme {
        val puppy = PuppyProvider.randomPuppy()
        PuppyItem(puppy = puppy) { }
    }
}
