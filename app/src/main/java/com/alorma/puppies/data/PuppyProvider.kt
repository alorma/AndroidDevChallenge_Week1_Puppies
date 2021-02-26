package com.alorma.puppies.data

import com.alorma.puppies.ui.model.*

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
        ),
        PuppyItemModel(
            id = PuppyId(2),
            name = "Menta",
            image = "https://random.dog/f0ba87f4-d2f3-4430-8727-6b2f6a49dfe7.jpg",
            gender = GenderType.MALE,
            breed = breedsList[0],
            age = 2,
        ),
    )

    fun getAllPuppies(
        breeds: List<BreedId>,
        genders: List<GenderType>,
        ages: Pair<Int, Int>,
    ): List<PuppyItemModel> {
        return puppiesList.filter { puppy ->
            val isBreed = if (breeds.isEmpty()) true else puppy.breed.id in breeds
            val isGender = if (genders.isEmpty()) true else puppy.gender in genders
            val isAge = puppy.age >= ages.first && puppy.age <= ages.second

            isBreed && isGender && isAge
        }
    }

    fun randomPuppy(): PuppyItemModel = puppiesList.random()

    fun getAllBreeds(): List<BreedItemModel> = breedsList

    fun getAllGenders(): List<GenderType> = GenderType.values().toList()

    fun getPuppy(puppyId: PuppyId): PuppyItemModel =
        puppiesList.first { it.id.value == puppyId.value }
}