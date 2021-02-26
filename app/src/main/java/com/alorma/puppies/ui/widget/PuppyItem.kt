package com.alorma.puppies.ui.widget

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.alorma.puppies.data.PuppyProvider
import com.alorma.puppies.ui.base.modifier.primaryClick
import com.alorma.puppies.ui.model.PuppyItemModel
import com.alorma.puppies.ui.theme.PuppiesTheme

@Composable
fun PuppyItem(
    puppy: PuppyItemModel,
    onClick: () -> Unit,
) {
    val avatarSize = 64.dp
    Box(
        modifier = Modifier.padding(
            start = 8.dp,
            top = 2.dp,
            end = 8.dp
        )
    ) {
        //PuppyItemCard(avatarSize, puppy, onClick = onClick)
        PuppyAvatar(
            modifier = Modifier
                .height(180.dp)
                .width(120.dp)
                .clip(shape = MaterialTheme.shapes.medium)
                .shadow(elevation = 4.dp, shape = MaterialTheme.shapes.medium),
            puppy = puppy
        )
    }
}

@Composable
private fun PuppyItemCard(
    avatarSize: Dp,
    puppy: PuppyItemModel,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(
                top = avatarSize / 2,
                start = avatarSize / 2,
                end = 8.dp,
                bottom = 8.dp,
            )
            .fillMaxWidth()
            .primaryClick { onClick() },
        shape = MaterialTheme.shapes.medium,
        elevation = 4.dp,
    ) {
        Column(
            modifier = Modifier.padding(
                top = 2.dp,
                start = 0.dp,
                end = 8.dp,
                bottom = 8.dp
            )
        ) {
            Column {
                Text(
                    modifier = Modifier
                        .padding(start = avatarSize / 2)
                        .fillMaxWidth(),
                    text = puppy.name,
                    style = MaterialTheme.typography.h4,
                )
                Text(
                    modifier = Modifier
                        .padding(start = (avatarSize / 2) - 8.dp)
                        .fillMaxWidth(),
                    text = "Breed:",
                    style = MaterialTheme.typography.body1,
                )
                Text(
                    modifier = Modifier
                        .padding(start = (avatarSize / 2) - 8.dp)
                        .fillMaxWidth(),
                    text = "Age:",
                    style = MaterialTheme.typography.body1,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewPuppy() {
    PuppiesTheme {
        val puppy = PuppyProvider.randomPuppy()
        PuppyItem(puppy = puppy) { }
    }
}