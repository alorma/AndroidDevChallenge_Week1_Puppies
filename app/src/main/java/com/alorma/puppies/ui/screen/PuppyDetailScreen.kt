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
package com.alorma.puppies.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.alorma.puppies.data.PuppyProvider
import com.alorma.puppies.ui.base.modifier.primaryClick
import com.alorma.puppies.ui.base.widget.ChipGroup
import com.alorma.puppies.ui.model.PuppyId
import com.alorma.puppies.ui.model.PuppyItemModel
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.chrisbanes.accompanist.insets.statusBarsPadding
import java.util.Locale

@Composable
fun PuppyDetailScreen(
    navController: NavController,
    puppyId: PuppyId
) {
    val puppy = PuppyProvider.getPuppy(puppyId)
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Box {
            PuppyHeader(
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .requiredHeight(280.dp)
                    .clipToBounds(),
                puppy = puppy,
                navController = navController
            )
            Column(
                modifier = Modifier.padding(
                    top = 220.dp
                ),
            ) {
                PuppyDetailCard(puppy)
                PuppyContent()
            }
        }
    }
}

@Composable
fun PuppyHeader(
    modifier: Modifier,
    puppy: PuppyItemModel,
    navController: NavController
) {
    Surface(modifier = modifier.fillMaxWidth()) {
        if (puppy.image != null) {
            key(puppy.image) {
                CoilImage(
                    data = puppy.image,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxSize(),
                    contentDescription = "Puppy im age"
                )
            }
            Box(modifier = Modifier.statusBarsPadding()) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "back",
                    modifier = Modifier
                        .padding(8.dp)
                        .size(32.dp)
                        .background(
                            color = MaterialTheme.colors.surface,
                            shape = MaterialTheme.shapes.small,
                        )
                        .primaryClick { navController.popBackStack() }
                        .padding(4.dp),
                )
            }
        }
    }
}

@Composable
fun PuppyDetailCard(puppy: PuppyItemModel) {
    Card(
        modifier = Modifier
            .padding(
                horizontal = 16.dp
            )
            .requiredHeight(100.dp)
            .fillMaxWidth(),
        elevation = 8.dp,
    ) {
        Box(
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 8.dp,
            )
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = puppy.name,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = puppy.breed.name,
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.40f)
                )
            }

            Row(
                modifier = Modifier.align(Alignment.BottomStart),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${puppy.age} years",
                    style = MaterialTheme.typography.caption,
                )
                Text(
                    text = " · ",
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = puppy.gender.name.capitalize(Locale.getDefault()),
                    style = MaterialTheme.typography.caption,
                )
            }
        }
    }
}

@Composable
fun PuppyContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
    ) {
        Column {
            ChipGroup(
                items = listOf(
                    "Active",
                    "Friendly",
                    "Loyal"
                ),
                selectedItems = remember { mutableStateListOf() },
                itemFormatter = { it },
                onSelectionChanged = { }
            )

            Text(text = LIPSUM, style = MaterialTheme.typography.body2)
        }

        val favState = remember { mutableStateOf(false) }
        Row(
            modifier = Modifier.align(Alignment.BottomEnd),
        ) {
            IconButton(
                modifier = Modifier.size(48.dp),
                onClick = { favState.value = !favState.value },
            ) {
                Icon(
                    imageVector = if (favState.value) {
                        Icons.Default.Favorite
                    } else {
                        Icons.Default.FavoriteBorder
                    },
                    contentDescription = "Favorite",
                    tint = if (favState.value) {
                        MaterialTheme.colors.primary
                    } else {
                        MaterialTheme.colors.onBackground
                    },
                )
            }
            Button(
                modifier = Modifier
                    .requiredHeight(48.dp)
                    .fillMaxWidth(),
                onClick = { }
            ) {
                Text("Adopt Now")
            }
        }
    }
}

private const val LIPSUM = """
Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum congue tempus purus. Ut sed purus eu diam dictum dictum. Cras ut accumsan urna. Ut vulputate eget neque ut porta. Donec in dignissim urna. Mauris pellentesque mollis tristique. Morbi dapibus venenatis facilisis. Morbi ligula nisi, laoreet non ullamcorper sit amet, laoreet vel dui. Vestibulum blandit est a lectus luctus placerat. Ut sollicitudin elit at quam consequat fringilla.
"""
