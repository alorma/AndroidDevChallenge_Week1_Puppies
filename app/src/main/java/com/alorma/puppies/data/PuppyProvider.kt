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
package com.alorma.puppies.data

import com.alorma.puppies.R
import com.alorma.puppies.ui.model.AnimalType
import com.alorma.puppies.ui.model.AnimalTypeId
import com.alorma.puppies.ui.model.BreedId
import com.alorma.puppies.ui.model.BreedItemModel
import com.alorma.puppies.ui.model.GenderType
import com.alorma.puppies.ui.model.PuppyId
import com.alorma.puppies.ui.model.PuppyItemModel

object PuppyProvider {

    private val breedsList = listOf(
        BreedItemModel(
            id = BreedId(1),
            name = "Labrador",
        ),
        BreedItemModel(
            id = BreedId(2),
            name = "Terrier",
        ),
        BreedItemModel(
            id = BreedId(3),
            name = "German Shepard",
        ),
    )

    private val puppiesList = listOf(
        PuppyItemModel(
            id = PuppyId(1),
            name = "Gamba",
            image = "https://random.dog/00186969-c51d-462b-948b-30a7e1735908.jpg",
            gender = GenderType.FEMALE,
            breed = breedsList[1],
            age = 9,
            icon = R.drawable.ic_dog,
        ),
        PuppyItemModel(
            id = PuppyId(2),
            name = "Menta",
            image = "https://random.dog/f0ba87f4-d2f3-4430-8727-6b2f6a49dfe7.jpg",
            gender = GenderType.MALE,
            breed = breedsList[0],
            age = 2,
            icon = R.drawable.ic_dog,
        ),
    )

    private val animalTypes = listOf(
        AnimalType(
            id = AnimalTypeId("all"),
            name = "All",
            icon = R.drawable.ic_pawn
        ),
        AnimalType(
            id = AnimalTypeId("dog"),
            name = "Dog",
            icon = R.drawable.ic_dog
        ),
        AnimalType(
            id = AnimalTypeId("cat"),
            name = "Cat",
            icon = R.drawable.ic_cat
        ),
        AnimalType(
            id = AnimalTypeId("bird"),
            name = "Bird",
            icon = R.drawable.ic_bird
        ),
    )

    fun getAllPuppies(): List<PuppyItemModel> = puppiesList

    fun randomPuppy(): PuppyItemModel = puppiesList.random()

    fun getAllAnimalTypes(): List<AnimalType> = animalTypes

    fun getPuppy(puppyId: PuppyId): PuppyItemModel =
        puppiesList.first { it.id.value == puppyId.value }
}
