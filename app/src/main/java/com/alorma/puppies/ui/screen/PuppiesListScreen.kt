package com.alorma.puppies.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.alorma.puppies.Navigation
import com.alorma.puppies.data.PuppyProvider
import com.alorma.puppies.ui.model.BreedItemModel
import com.alorma.puppies.ui.model.GenderType
import com.alorma.puppies.ui.model.PuppyItemModel
import com.alorma.puppies.ui.widget.FilterChip
import com.alorma.puppies.ui.widget.PuppyItem
import com.google.android.material.slider.LabelFormatter
import com.google.android.material.slider.RangeSlider
import dev.chrisbanes.accompanist.insets.statusBarsPadding
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PuppiesListScreen(
    navController: NavController
) {
    val selectedBreedFilters: MutableState<List<BreedItemModel>> =
        remember { mutableStateOf(listOf()) }

    val selectedGenderFilters: MutableState<List<GenderType>> =
        remember { mutableStateOf(listOf()) }

    val minMaxAges = 0 to 14
    val selectedAgesFilters: MutableState<Pair<Int, Int>> =
        remember { mutableStateOf(minMaxAges) }

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier.background(color = MaterialTheme.colors.primary)
            ) {
                TopAppBar(
                    modifier = Modifier.statusBarsPadding(),
                    title = { Text(text = "Puppies list") },
                    backgroundColor = MaterialTheme.colors.primary,
                    actions = { FilterButton {} }
                )
            }
        }
    ) {
        val puppiesList = PuppyProvider.getAllPuppies(
            breeds = selectedBreedFilters.value.map { breed -> breed.id },
            genders = selectedGenderFilters.value,
            ages = selectedAgesFilters.value,
        )
        PuppiesList(
            puppies = puppiesList,
            navController = navController
        )
    }

    /*
                PuppiesFilters(
                breeds = PuppyProvider.getAllBreeds(),
                genders = PuppyProvider.getAllGenders(),
                ages = minMaxAges,
                selectedBreedFilters = selectedBreedFilters,
                selectedGenderFilters = selectedGenderFilters,
                selectedAgesFilters = selectedAgesFilters,
            )
     */
}

@Composable
private fun FilterButton(
    onClick: () -> Unit,
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Default.FilterList,
            contentDescription = "Filter"
        )
    }
}

@Composable
private fun PuppiesFilters(
    breeds: List<BreedItemModel>,
    genders: List<GenderType>,
    ages: Pair<Int, Int>,
    selectedBreedFilters: MutableState<List<BreedItemModel>>,
    selectedGenderFilters: MutableState<List<GenderType>>,
    selectedAgesFilters: MutableState<Pair<Int, Int>>
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Text(text = "Breed", style = MaterialTheme.typography.subtitle1)
        ChipFilters(
            items = breeds,
            selectedItems = selectedBreedFilters.value,
            itemFormatter = { breed -> breed.name }
        ) { selectedBreeds -> selectedBreedFilters.value = selectedBreeds }

        Text(text = "Gender", style = MaterialTheme.typography.subtitle1)
        ChipFilters(
            items = genders,
            selectedItems = selectedGenderFilters.value,
            itemFormatter = { gender -> gender.name.capitalize(Locale.getDefault()) }
        ) { selectedGender -> selectedGenderFilters.value = selectedGender }

        Text(
            text = "Age: (${selectedAgesFilters.value.first} to ${selectedAgesFilters.value.second})",
            style = MaterialTheme.typography.subtitle1
        )
        Ranger(ages) { (min, max) ->
            selectedAgesFilters.value = min to max
        }
    }
}

@Composable
private fun Ranger(
    range: Pair<Int, Int>,
    onRangeChanged: (Pair<Int, Int>) -> Unit,
) {
    AndroidView(factory = { context ->
        val rangeSlider = RangeSlider(context)

        rangeSlider.values = listOf(range.first, range.second).map { it.toFloat() }
        rangeSlider.stepSize = 1f
        rangeSlider.valueFrom = range.first.toFloat()
        rangeSlider.valueTo = range.second.toFloat()

        rangeSlider.labelBehavior = LabelFormatter.LABEL_WITHIN_BOUNDS
        rangeSlider.setLabelFormatter { years ->
            "${years.toInt()} years"
        }

        rangeSlider.addOnSliderTouchListener(object : RangeSlider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: RangeSlider) {}

            override fun onStopTrackingTouch(slider: RangeSlider) {}
        })

        rangeSlider.addOnChangeListener { slider, _, fromUser ->
            if (fromUser) {
                onRangeChanged(slider.values[0].toInt() to slider.values[1].toInt())
            }
        }
        rangeSlider
    })
}

@Composable
private fun <T> ChipFilters(
    items: List<T>,
    selectedItems: List<T>,
    itemFormatter: (T) -> String,
    onBreedSelectionChanged: (List<T>) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
    ) {
        itemsIndexed(items) { index, item ->
            FilterChip(
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

@Composable
private fun PuppiesList(
    puppies: List<PuppyItemModel>,
    navController: NavController
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp)
    ) {
        itemsIndexed(puppies) { index, puppy ->
            key(puppy.id.value) {
                PuppyItem(
                    puppy = puppy,
                    onClick = {
                        navController.navigate(Navigation.buildPuppyDetailPath(puppyId = puppy.id))
                    }
                )
                if (index < puppies.size) {
                    Spacer(modifier = Modifier.requiredHeight(8.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun lightPreviewPuppiesListScreen() {
    PuppiesListScreen(navController = rememberNavController())
}