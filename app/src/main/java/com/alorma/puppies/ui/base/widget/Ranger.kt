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
package com.alorma.puppies.ui.base.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.material.slider.LabelFormatter
import com.google.android.material.slider.RangeSlider

@Composable
private fun Ranger(
    range: Pair<Int, Int>,
    onRangeChanged: (Pair<Int, Int>) -> Unit,
) {
    AndroidView(
        factory = { context ->
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
        }
    )
}
