package kr.twothumb.butterfly.ui.component.toolbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun CollapsingToolbarScaffoldLayout(
    modifier: Modifier = Modifier.fillMaxSize(),
    toolbarMinHeight: Dp,
    collapsedHeader: @Composable () -> Unit,
    expendedHeader: @Composable CollapsingToolbarScope.() -> Unit,
    content: LazyListScope.() -> Unit
) {
    val state = rememberCollapsingToolbarScaffoldState()
    var enabled by remember { mutableStateOf(true) }

    Surface(modifier = modifier.background(MaterialTheme.colorScheme.tertiaryContainer)) {
        CollapsingToolbarScaffold(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.onPrimary),
            state = state,
            scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
            toolbarModifier = Modifier.background(MaterialTheme.colorScheme.primary),
            enabled = enabled,
            toolbar = {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(toolbarMinHeight)
                )
                expendedHeader()
            }
        ) {
            Surface(modifier = Modifier.background(color = MaterialTheme.colorScheme.tertiaryContainer)) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    content()
                }
            }
        }

        collapsedHeader()
    }
}
