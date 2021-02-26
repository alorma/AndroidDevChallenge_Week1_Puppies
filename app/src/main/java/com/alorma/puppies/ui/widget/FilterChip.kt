package com.alorma.puppies.ui.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alorma.puppies.ui.theme.PuppiesTheme

@Composable
fun FilterChip(
    text: String,
    selected: Boolean,
    onSelectedChanged: (Boolean) -> Unit
) {
    val color = if (selected) {
        MaterialTheme.colors.onPrimary
    } else {
        MaterialTheme.colors.onPrimary.copy(alpha = 0.20f)
    }

    val borderColor = if (selected) {
        MaterialTheme.colors.onPrimary
    } else {
        MaterialTheme.colors.onPrimary.copy(alpha = 0.20f)
    }

    Surface(
        color = color,
        shape = ChipShape,
        border = BorderStroke(width = 1.dp, color = borderColor),
        modifier = Modifier
            .clip(ChipShape)
            .selectable(
                selected = selected,
                role = Role.Checkbox,
                onClick = { onSelectedChanged(!selected) }
            )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

val ChipShape = RoundedCornerShape(percent = 50)

@Preview(showBackground = true)
@Composable
fun previewFilterChip() {
    PuppiesTheme {
        Box(modifier = Modifier.padding(8.dp)) {
            FilterChip(
                text = "Test",
                selected = false,
            ) { }
        }
    }
}