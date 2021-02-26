package com.alorma.puppies.ui.base.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alorma.puppies.R
import com.alorma.puppies.ui.theme.PuppiesTheme

@Composable
fun Chip(
    text: String,
    icon: Int? = null,
    selected: Boolean,
    onSelectedChanged: (Boolean) -> Unit
) {
    val color = if (selected) {
        MaterialTheme.colors.secondary
    } else {
        MaterialTheme.colors.onBackground.copy(alpha = 0.20f)
    }

    val textColor = if (selected) {
        MaterialTheme.colors.onSecondary
    } else {
        MaterialTheme.colors.onBackground
    }

    Surface(
        color = color,
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .clip(MaterialTheme.shapes.small)
            .selectable(
                selected = selected,
                role = Role.Checkbox,
                onClick = { onSelectedChanged(!selected) }
            )
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            if (icon != null) {
                Icon(painter = painterResource(id = icon), contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = text,
                style = MaterialTheme.typography.body2,
                color = textColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewUnSelectedChip() {
    PuppiesTheme {
        Chip(
            text = "UnSelected",
            icon = R.drawable.ic_pawn,
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
