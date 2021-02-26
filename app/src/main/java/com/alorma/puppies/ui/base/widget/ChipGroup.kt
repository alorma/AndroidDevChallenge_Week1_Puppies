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
    modifier : Modifier = Modifier,
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