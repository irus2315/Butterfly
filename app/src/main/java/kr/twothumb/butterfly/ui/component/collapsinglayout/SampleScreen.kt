package kr.twothumb.butterfly.ui.component.collapsinglayout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.twothumb.butterfly.ui.component.collapsinglayout.sample.AnimalCard
import kr.twothumb.butterfly.ui.component.collapsinglayout.sample.LazyCatalog
import kr.twothumb.butterfly.ui.component.collapsinglayout.scrollflags.ExitUntilCollapsedState

private val MinToolbarHeight = 96.dp
private val MaxToolbarHeight = 176.dp

@Composable
private fun rememberToolbarState(toolbarHeightRange: IntRange): ToolbarState {
    return rememberSaveable(saver = ExitUntilCollapsedState.Saver) {
        ExitUntilCollapsedState(toolbarHeightRange)
    }
}

@Composable
fun Catalog(
    modifier: Modifier = Modifier
) {

    val toolbarHeightRange = with(LocalDensity.current) {
        MinToolbarHeight.roundToPx()..MaxToolbarHeight.roundToPx()
    }
    val toolbarState = rememberToolbarState(toolbarHeightRange)
    val scrollState = rememberScrollState()
    toolbarState.scrollValue = scrollState.value

    Box(modifier = modifier) {
        LazyCatalog(Modifier.fillMaxSize())
        CollapsingToolbar(
            progress = toolbarState.progress,
            modifier = Modifier
                .fillMaxWidth()
                .height(with(LocalDensity.current) { toolbarState.height.toDp() })
                .graphicsLayer { translationY = toolbarState.offset }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CollapseLayoutPreview() {
    Catalog()
}