package com.alorma.puppies.extensions

import androidx.navigation.NavBackStackEntry
import com.alorma.puppies.Navigation
import com.alorma.puppies.ui.model.PuppyId

fun NavBackStackEntry.getPuppyIdArgument(key: String) =
    arguments?.getInt(Navigation.NAV_PUPPY_DETAIL_ARGUMENT)?.let {
        PuppyId(it)
    } ?: error("$key not provided")