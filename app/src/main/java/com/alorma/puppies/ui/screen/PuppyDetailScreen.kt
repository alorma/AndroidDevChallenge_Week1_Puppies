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
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.alorma.puppies.data.PuppyProvider
import com.alorma.puppies.ui.base.modifier.onSurfaceClick
import com.alorma.puppies.ui.base.modifier.primaryClick
import com.alorma.puppies.ui.base.widget.ChipGroup
import com.alorma.puppies.ui.model.PuppyId
import com.alorma.puppies.ui.model.PuppyItemModel
import com.alorma.puppies.ui.widget.PuppyAvatar
import dev.chrisbanes.accompanist.insets.statusBarsPadding

@Composable
fun PuppyDetailScreen(
    navController: NavController,
    puppyId: PuppyId
) {
    val puppy = PuppyProvider.getPuppy(puppyId)

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
        ) {
            PuppyHeader(
                modifier = Modifier.requiredHeight(280.dp),
                puppy = puppy,
                navController = navController
            )
            PuppyContent()
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
                PuppyAvatar(
                    modifier = Modifier.fillMaxSize(),
                    puppy = puppy,
                )
            }
            Box(
                modifier = Modifier.statusBarsPadding()
            ) {
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
fun PuppyContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column {
            Column(
                modifier = Modifier.padding(16.dp),
            ) {
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

                Text(
                    text = LIPSUM,
                    style = MaterialTheme.typography.body2
                )
            }

            LazyColumn {
                val infos = listOf(
                    "Puppy information",
                    "Agency contact"
                )
                itemsIndexed(infos) { index, item ->
                    if (index == 0) {
                        Divider(modifier = Modifier.fillMaxWidth())
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .requiredHeight(56.dp)
                            .onSurfaceClick { }
                            .padding(horizontal = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowDropDown,
                            contentDescription = null
                        )
                        Text(text = item)
                    }
                    if (index <= infos.size) {
                        Divider(modifier = Modifier.fillMaxWidth())
                    }
                }
            }
        }

        PuppyDetailButtons()
    }
}

@Composable
private fun BoxScope.PuppyDetailButtons() {
    val favState = remember { mutableStateOf(false) }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomEnd),
        color = MaterialTheme.colors.primary,
        shape = MaterialTheme.shapes.medium.copy(
            bottomStart = CornerSize(0.dp),
            bottomEnd = CornerSize(0.dp),
        )
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 8.dp,
            ),
        ) {
            OutlinedButton(
                modifier = Modifier.size(48.dp),
                onClick = { favState.value = !favState.value },
                elevation = ButtonDefaults.elevation()
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
            Spacer(modifier = Modifier.requiredWidth(8.dp))
            Button(
                modifier = Modifier
                    .requiredHeight(48.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.surface
                ),
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
