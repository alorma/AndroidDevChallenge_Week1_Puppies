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

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.alorma.puppies.Navigation
import com.alorma.puppies.R
import com.alorma.puppies.data.PuppyProvider
import com.alorma.puppies.data.UserProvider
import com.alorma.puppies.ui.base.modifier.onSurfaceClick
import com.alorma.puppies.ui.base.modifier.primaryClick
import com.alorma.puppies.ui.base.widget.ChipGroup
import com.alorma.puppies.ui.model.AnimalType
import com.alorma.puppies.ui.theme.PuppiesTheme
import com.alorma.puppies.ui.widget.PuppyItem
import com.alorma.puppies.ui.widget.UserAvatar
import dev.chrisbanes.accompanist.insets.statusBarsPadding

@Composable
fun PuppiesListScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colors.background)
            .statusBarsPadding()
    ) {
        PuppiesListTopBar()
        Column(
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            PuppiesListPremiumBanner()
            Spacer(modifier = Modifier.height(12.dp))
            PuppiesListSearchBar()
            Spacer(modifier = Modifier.requiredHeight(16.dp))
            PuppiesListFilters()
            Spacer(modifier = Modifier.height(12.dp))
        }
        PuppiesList(navController = navController)
    }
}

@Composable
fun PuppiesListTopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(56.dp)
            .padding(8.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        Row(
            modifier = Modifier
                .border(
                    border = BorderStroke(
                        width = 1.dp,
                        color = MaterialTheme.colors.onBackground.copy(alpha = 0.40f)
                    ),
                    shape = CircleShape,
                )
                .clip(CircleShape)
                .onSurfaceClick { }
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                tint = MaterialTheme.colors.onBackground,
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Barcelona",
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.subtitle1,
            )
            Spacer(modifier = Modifier.width(2.dp))
            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = Icons.Outlined.ArrowDropDown,
                contentDescription = null,
                tint = MaterialTheme.colors.onBackground,
            )
        }
        UserAvatar(
            modifier = Modifier
                .size(32.dp)
                .align(Alignment.TopEnd)
                .clip(CircleShape)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colors.primary,
                    shape = CircleShape
                )
                .onSurfaceClick { },
            user = UserProvider.user,
        )
    }
}

@Composable
fun PuppiesListPremiumBanner() {
    Box {
        Card(
            modifier = Modifier
                .padding(top = 32.dp)
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            elevation = 8.dp,
        ) {
            Column(
                modifier = Modifier.padding(
                    top = 12.dp,
                    bottom = 12.dp,
                    start = 12.dp,
                    end = 116.dp,
                ),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = "Become premium with us",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.subtitle1,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Access more popular adopter and pets by upgrading to premium",
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onBackground.copy(alpha = 0.50f),
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.primary
                    )
                ) {
                    Text(text = "Become Premium")
                }
            }
        }
        Image(
            modifier = Modifier
                .height(100.dp)
                .width(90.dp)
                .align(Alignment.TopEnd),
            painter = painterResource(id = R.drawable.premium_dog),
            contentDescription = null
        )
    }
}

@Composable
fun PuppiesListSearchBar() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        elevation = 8.dp,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .primaryClick { }
                .padding(
                    horizontal = 16.dp,
                    vertical = 2.dp
                ),
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colors.onSurface.copy(alpha = 0.50f),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Search pet to adopt",
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.40f),
                style = MaterialTheme.typography.subtitle1,
            )
        }
    }
}

@Composable
fun PuppiesListFilters() {
    val selectedBreedFilters: MutableState<List<AnimalType>> =
        remember {
            mutableStateOf(
                listOf(
                    PuppyProvider.getAllAnimalTypes().first()
                )
            )
        }

    ChipGroup(
        items = PuppyProvider.getAllAnimalTypes(),
        selectedItems = selectedBreedFilters.value,
        itemFormatter = { it.name },
        itemIconFormatter = { it.icon },
        onSelectionChanged = { selectedBreeds ->
            selectedBreedFilters.value = selectedBreeds
        }
    )
}

@Composable
fun PuppiesList(navController: NavController) {
    val puppiesList = PuppyProvider.getAllPuppies()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp)
    ) {
        itemsIndexed(puppiesList) { index, puppy ->
            key(puppy.id.value) {
                PuppyItem(
                    puppy = puppy,
                    onClick = {
                        navController.navigate(Navigation.buildPuppyDetailPath(puppyId = puppy.id))
                    }
                )
                if (index < puppiesList.size) {
                    Spacer(modifier = Modifier.requiredHeight(16.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun preview() {
    PuppiesTheme {
        PuppiesListScreen(navController = rememberNavController())
    }
}
