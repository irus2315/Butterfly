package kr.twothumb.butterfly.common

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing

object AnimationSpec {
    var FocusDuration = 800
    val FocusEasing: Easing = CubicBezierEasing(0f, .8f, .5f, 1f)
}

/**
 * ui
 */

