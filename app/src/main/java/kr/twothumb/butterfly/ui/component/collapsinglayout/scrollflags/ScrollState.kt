package kr.twothumb.butterfly.ui.component.collapsinglayout.scrollflags

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.structuralEqualityPolicy
import kr.twothumb.butterfly.ui.component.collapsinglayout.ScrollFlagState

class ScrollState(
    heightRange: IntRange,
    scrollValue: Int = 0
) : ScrollFlagState(heightRange, scrollValue) {

    override val offset: Float
        get() = -(scrollValue - rangeDifference.toFloat()).coerceIn(0f, minHeight.toFloat())

    override val height: Float
        get() = (maxHeight.toFloat() - scrollValue).coerceIn(minHeight.toFloat(), maxHeight.toFloat())

    override var scrollValue: Int
        get() = _scrollValue
        set(value) {
            _scrollValue = value.coerceAtLeast(0)
        }

    companion object {
        val Saver = run {
            val minHeightKey = "MinHeight"
            val maxHeightKey = "MaxHeight"
            val scrollValueKey = "ScrollValue"

            mapSaver(
                save = {
                    mapOf(
                        minHeightKey to it.minHeight,
                        maxHeightKey to it.maxHeight,
                        scrollValueKey to it.scrollValue
                    )
                },
                restore = {
                    ScrollState(
                        heightRange = (it[minHeightKey] as Int)..(it[maxHeightKey] as Int),
                        scrollValue = it[scrollValueKey] as Int
                    )
                }
            )
        }
    }
}