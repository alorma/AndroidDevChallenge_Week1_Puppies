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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.alorma.drawer_base.compositeOverSurface
import com.alorma.puppies.data.PuppyProvider
import com.alorma.puppies.ui.base.modifier.primaryClick
import com.alorma.puppies.ui.model.PuppyId
import com.alorma.puppies.ui.model.PuppyItemModel
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.chrisbanes.accompanist.insets.statusBarsPadding

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
            val shape = MaterialTheme.shapes.medium.copy(
                topStart = CornerSize(0.dp),
                topEnd = CornerSize(0.dp),
            )
            PuppyHeader(
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .requiredHeight(200.dp)
                    .clip(shape)
                    .clipToBounds(),
                puppy = puppy,
                navController = navController
            )
            Column {
                PuppyDetailCard(puppy)
                PuppyContent(puppy)
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
                top = 150.dp,
                start = 24.dp,
                end = 24.dp
            )
            .requiredHeight(100.dp)
            .fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.primary.copy(alpha = 0.12f).compositeOverSurface(),
        contentColor = MaterialTheme.colors.onPrimary,
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            text = puppy.name,
            style = MaterialTheme.typography.h4,
        )
    }
}

@Composable
fun PuppyContent(puppy: PuppyItemModel) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(text = LIPSUM, style = MaterialTheme.typography.body2)
    }
}

private const val LIPSUM = """
Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum congue tempus purus. Ut sed purus eu diam dictum dictum. Cras ut accumsan urna. Ut vulputate eget neque ut porta. Donec in dignissim urna. Mauris pellentesque mollis tristique. Morbi dapibus venenatis facilisis. Morbi ligula nisi, laoreet non ullamcorper sit amet, laoreet vel dui. Vestibulum blandit est a lectus luctus placerat. Ut sollicitudin elit at quam consequat fringilla.

Duis mollis dolor eu nibh viverra, in pulvinar libero laoreet. Etiam in convallis ante, a sodales massa. Maecenas imperdiet neque id nisl luctus commodo. Cras commodo quam ante, quis euismod orci tincidunt elementum. Nunc lacus justo, ultricies et efficitur luctus, condimentum et odio. Duis auctor, velit id convallis ornare, purus magna placerat orci, vitae fringilla dui sapien eget mauris. Fusce sodales, nibh vel malesuada interdum, lacus risus sagittis felis, vel interdum urna felis at mauris. Aenean dignissim libero magna, a porttitor ex ullamcorper eget. Integer sed lobortis erat. Vestibulum pulvinar ipsum ac dolor consequat, vitae imperdiet leo dignissim. Praesent sodales vulputate dictum. Sed ultricies tellus arcu, nec tempus tellus volutpat eget. Donec vulputate placerat lacus, sit amet pharetra urna tempor sed. Praesent risus ex, aliquam id mi at, egestas mollis odio. Integer condimentum imperdiet tortor quis dapibus. Aenean et lorem non tellus mollis pulvinar sed eget lacus.
"""
