package com.alorma.puppies.ui.model

data class PuppyItemModel(
    val id: PuppyId,
    val name: String,
    val image: String? = null,
    val gender: GenderType,
    val breed: BreedItemModel,
    val age: Int
)

data class BreedItemModel(
    val id: BreedId,
    val name: String
)
