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