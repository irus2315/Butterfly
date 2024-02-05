package kr.twothumb.butterfly.ui.theme

import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Stable
interface Spaces {

    @Stable
    companion object {
        val ROUND_CORNER: Dp = 12.dp
        val LESS_ROUND_CORNER: Dp = 8.dp
        val BASE_PADDING: Dp = 12.dp

        val PADDING: Dp = 18.dp

        val LONG_PADDING: Dp = 24.dp
        val SMALL_GAP: Dp = 4.dp
        val LESS_SMALL_GAP: Dp = 2.dp
        val BASE_GAP: Dp = 8.dp
        val BASE_MARGIN: Dp = 24.dp
        val MINUS_GAP: Dp = -8.dp
    }
}