package com.alorma.puppies

import com.alorma.puppies.ui.model.PuppyId

object Navigation {
    const val NAV_PUPPY_LIST = "puppy_list"

    const val NAV_PUPPY_DETAIL_ARGUMENT = "puppyId"
    const val NAV_PUPPY_DETAIL = "puppy_detail/{$NAV_PUPPY_DETAIL_ARGUMENT}"

    fun buildPuppyDetailPath(puppyId: PuppyId) = "puppy_detail/${puppyId.value}"
}