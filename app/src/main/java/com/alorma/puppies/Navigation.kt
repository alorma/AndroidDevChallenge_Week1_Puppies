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
package com.alorma.puppies

import com.alorma.puppies.ui.model.PuppyId

object Navigation {
    const val NAV_PUPPY_LIST = "puppy_list"

    const val NAV_PUPPY_DETAIL_ARGUMENT = "puppyId"
    const val NAV_PUPPY_DETAIL = "puppy_detail/{$NAV_PUPPY_DETAIL_ARGUMENT}"

    fun buildPuppyDetailPath(puppyId: PuppyId) = "puppy_detail/${puppyId.value}"
}
