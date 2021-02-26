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

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun <T> ChipGroup(
    modifier: Modifier = Modifier,
    items: List<T>,
    selectedItems: List<T>,
    itemFormatter: (T) -> String,
    itemIconFormatter: (T) -> Int? = { null },
    onBreedSelectionChanged: (List<T>) -> Unit
) {
    LazyRow(modifier = modifier) {
        itemsIndexed(items) { index, item ->
            Chip(
                text = itemFormatter(item),
                icon = itemIconFormatter(item),
                selected = selectedItems.contains(item),
            ) { selected ->
                if (selected) {
                    onBreedSelectionChanged(selectedItems + item)
                } else {
                    onBreedSelectionChanged(selectedItems - item)
                }
            }

            if (index < items.size - 1) {
                Spacer(Modifier.width(8.dp))
            }
        }
    }
}
