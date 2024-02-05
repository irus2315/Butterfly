package kr.twothumb.butterfly.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextGeometricTransform
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import kr.twothumb.butterfly.R

val pretendard = FontFamily(
    Font(R.font.pretendard_gov_black, FontWeight.Black),
    Font(R.font.pretendard_gov_bold, FontWeight.Bold),
    Font(R.font.pretendard_gov_extrabold, FontWeight.ExtraBold),
    Font(R.font.pretendard_gov_extralight, FontWeight.ExtraLight),
    Font(R.font.pretendard_gov_light, FontWeight.Light),
    Font(R.font.pretendard_gov_medium, FontWeight.Medium),
    Font(R.font.pretendard_gov_regular, FontWeight.Normal),
    Font(R.font.pretendard_gov_thin, FontWeight.Thin),
    Font(R.font.pretendard_gov_semibold, FontWeight.SemiBold),
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.Normal,
        textAlign = TextAlign.Center,
    ),
    titleLarge = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Center,
    ),
    titleMedium = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Center,
    ),
    bodyLarge = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
    ),
    bodyMedium = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Center,
    ),

    labelLarge =TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
    ),

    labelMedium = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Center,
    ),
    labelSmall = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.Normal,
    ),
    headlineSmall = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.Black,
        textAlign = TextAlign.Start,
    ),

    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
    )
    */
)

val Int.textDp: TextUnit @Composable get() = this.textDp(density = LocalDensity.current)

private fun Int.textDp(density: Density): TextUnit = with(density) {
    this@textDp.dp.toSp()
}

val Float.textDp: TextUnit @Composable get() = this.textDp(density = LocalDensity.current)

private fun Float.textDp(density: Density): TextUnit = with(density) {
    this@textDp.dp.toSp()
}

@Composable
fun TextStyle.init(
    fontSize: TextUnit = 16.textDp,
    lineHeight: TextUnit = 18.textDp,
    color: Color = MaterialTheme.colorScheme.primary,
    textAlign: TextAlign = TextAlign.Center,
    letterSpacing: TextUnit = 0.textDp
): TextStyle {
    return copy(
        fontSize = fontSize,
        lineHeight = lineHeight,
        color = color,
        textAlign = textAlign,
        letterSpacing = letterSpacing
    )
}

val TextStyle.displayLarge: TextStyle
    @Composable get() {
        return copy(
            fontSize = 36.textDp,
            lineHeight = 42.textDp,
            color = MaterialTheme.colorScheme.inversePrimary,
        )
    }

val TextStyle.buttonText: TextStyle
    @Composable get() {
        return copy(
            fontSize = 18.textDp,
            lineHeight = 20.textDp,
            color = MaterialTheme.colorScheme.inversePrimary,
        ).base
    }
val TextStyle.labelLarge: TextStyle
    @Composable get() {
        return copy(
            fontSize = 16.textDp,
            lineHeight = 18.textDp,
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Start
        ).base
    }

val TextStyle.labelMedium: TextStyle
    @Composable get() {
        return copy(
            fontSize = 14.textDp,
            lineHeight = 16.textDp,
            color = MaterialTheme.colorScheme.onSecondary,
            textAlign = TextAlign.Start
        ).base
    }

val TextStyle.labelMediumNumber: TextStyle
    @Composable get() {
        return copy(
            fontSize = 14.textDp,
            lineHeight = 16.textDp,
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Start
        ).base
    }

val TextStyle.labelSmall: TextStyle
    @Composable get() {
        return copy(
            fontSize = 12.textDp,
            lineHeight = 14.textDp,
            color = MaterialTheme.colorScheme.onTertiary,
            textAlign = TextAlign.Start
        ).base
    }
val TextStyle.labelMoreSmall: TextStyle
    @Composable get() {
        return copy(
            fontSize = 10.textDp,
            lineHeight = 12.textDp,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Start
        ).base
    }

val TextStyle.labelTag: TextStyle
    @Composable get() {
        return copy(
            letterSpacing = (0).textDp,
            textGeometricTransform = TextGeometricTransform(.92f),
            fontSize = 10.textDp,
            lineHeight = 12.textDp,
            color = MaterialTheme.colorScheme.onSecondary,
            textAlign = TextAlign.Start,
        ).base
    }

val TextStyle.labelTagInverse: TextStyle
    @Composable get() {
        return copy(
            letterSpacing = (0).textDp,
            textGeometricTransform = TextGeometricTransform(.92f),
            fontSize = 10.textDp,
            lineHeight = 12.textDp,
            color = MaterialTheme.colorScheme.inversePrimary,
            textAlign = TextAlign.Start,
        ).base
    }

val TextStyle.secondary: TextStyle
    @Composable get() {
        return copy(
            color = MaterialTheme.colorScheme.secondary,
        )
    }

val TextStyle.onSecondary: TextStyle
    @Composable get() {
        return copy(
            color = MaterialTheme.colorScheme.onSecondary,
        )
    }

val TextStyle.caution: TextStyle
    @Composable get() {
        return copy(
            color = MaterialTheme.colorScheme.error,
        )
    }

val TextStyle.base: TextStyle
    @Composable get() {
        return copy(
            fontFamily = pretendard, letterSpacing = 0.5f.textDp,
            platformStyle = PlatformTextStyle(includeFontPadding = false),
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.None
            )
        )
    }
val TextStyle.baseStyle: TextStyle
    @Composable get() {
        val textStyle = when (this) {
            MaterialTheme.typography.displayLarge -> {
                copy(

                    textAlign = TextAlign.Start,
                    fontSize = 36.textDp,
                    lineHeight = 42.textDp,
                    color = MaterialTheme.colorScheme.inversePrimary,
                )
            }

            MaterialTheme.typography.titleLarge -> {
                copy(
                    fontSize = 18.textDp,
                    lineHeight = 20.textDp,
                    color = MaterialTheme.colorScheme.primary,
                )
            }

            MaterialTheme.typography.titleMedium -> {
                copy(
                    fontSize = 18.textDp,
                    lineHeight = 20.textDp,
                    color = MaterialTheme.colorScheme.inversePrimary,
                )
            }

            MaterialTheme.typography.bodyLarge -> {
                copy(
                    fontSize = 28.textDp,
                    lineHeight = 32.textDp,
                    color = MaterialTheme.colorScheme.primary,
                )
            }

            MaterialTheme.typography.bodyMedium -> {
                copy(
                    fontSize = 18.textDp,
                    lineHeight = 20.textDp,
                    color = MaterialTheme.colorScheme.primary,
                )
            }

            MaterialTheme.typography.labelSmall -> {
                copy(
                    fontSize = 12.textDp,
                    lineHeight = 14.textDp,
                    color = MaterialTheme.colorScheme.secondary,
                )

            }

            else -> {
                MaterialTheme.typography.bodyLarge
            }
        }
        return textStyle.base
    }