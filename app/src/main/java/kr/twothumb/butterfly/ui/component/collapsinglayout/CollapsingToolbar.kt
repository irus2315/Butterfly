package kr.twothumb.butterfly.ui.component.collapsinglayout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PrivacyTip
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.util.lerp

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import kr.twothumb.butterfly.R
import kr.twothumb.butterfly.ui.theme.ButterflyTheme
import kotlin.math.roundToInt

private val ContentPadding = 8.dp
private val Elevation = 4.dp
private const val Alpha = 0.75f

@Composable
fun CollapsingToolbar(
    progress: Float,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colors.primary,
        elevation = Elevation,
        modifier = modifier
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            //#region Background Image
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,

                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        alpha = progress * Alpha
                    },
                alignment = BiasAlignment(0f, 1f - ((1f - progress) * 0.75f))
            )
            //#endregion
            Box(
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(horizontal = ContentPadding)
                    .fillMaxSize()
            ) {
                CollapsingToolbarLayout(progress = progress) {
                    Row(
                        modifier = Modifier.wrapContentSize(),
                        horizontalArrangement = Arrangement.spacedBy(ContentPadding)
                    ) {
                        Icon(
                            modifier = Modifier.fillMaxSize(),
                            imageVector = Icons.Rounded.PrivacyTip,
                            contentDescription = null,
                        )
                        Icon(
                            modifier = Modifier.fillMaxSize(),
                            imageVector = Icons.Rounded.Settings,
                            contentDescription = null,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CollapsingToolbarLayout(
    progress: Float,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        layout(
            width = constraints.maxWidth,
            height = constraints.maxHeight
        ) {
        }
    }
}


@Preview
@Composable
fun CollapsingToolbarCollapsedPreview() {
    ButterflyTheme {
        CollapsingToolbar(
            progress = 0f,
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
        )
    }
}

@Preview
@Composable
fun CollapsingToolbarHalfwayPreview() {
    ButterflyTheme {
        CollapsingToolbar(
            progress = 0.2f,
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
        )
    }
}

@Preview
@Composable
fun CollapsingToolbarExpandedPreview() {
    ButterflyTheme {
        CollapsingToolbar(
            progress = 1f,
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
        )
    }
}
