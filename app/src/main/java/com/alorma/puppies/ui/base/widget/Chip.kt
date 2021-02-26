package com.alorma.puppies.ui.base.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alorma.puppies.ui.theme.PuppiesTheme

@Composable
fun Chip(
    text: String,
    selected: Boolean,
    onSelectedChanged: (Boolean) -> Unit
) {
    val color = if (selected) {
        MaterialTheme.colors.secondary
    } else {
        MaterialTheme.colors.onBackground.copy(alpha = 0.20f)
    }

    val borderColor = if (selected) {
        MaterialTheme.colors.secondary
    } else {
        MaterialTheme.colors.onBackground.copy(alpha = 0.20f)
    }

    Surface(
        color = color,
        shape = MaterialTheme.shapes.small,
        border = BorderStroke(width = 1.dp, color = borderColor),
        modifier = Modifier
            .clip(MaterialTheme.shapes.small)
            .selectable(
                selected = selected,
                role = Role.Checkbox,
                onClick = { onSelectedChanged(!selected) }
            )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.body2,
            color = contentColorFor(backgroundColor = color),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun previewUnSelectedChip() {
    PuppiesTheme {
        Chip(
            text = "UnSelected",
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
