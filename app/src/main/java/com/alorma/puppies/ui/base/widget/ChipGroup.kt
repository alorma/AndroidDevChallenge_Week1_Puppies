package com.alorma.puppies.ui.base.widget

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun <T> ChipGroup(
    items: List<T>,
    selectedItems: List<T>,
    itemFormatter: (T) -> String,
    onBreedSelectionChanged: (List<T>) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
    ) {
        itemsIndexed(items) { index, item ->
            Chip(
                text = itemFormatter(item),
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