package kr.twothumb.butterfly.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val Pink80 = Color(0xFFEFB8C8)

val Pink40 = Color(0xFF7D5260)

val Black = Color(0xFF212121)
val LightBackground = Color(0xFFDDDDDD)
val White = Color(0xFFFFFFFF)

val Grey = Color(0xFF6B6B6B)
val Grey1 = Color(0xFFCCCCCC)

val Transparent = Color(0x00000000)

val colorAccent = Color(0xFF10C684)

val colorCaution = Color(0xFFE42C2C)

val colorPrimary = Color(0xFF3f51b5)
val colorSecondary = Color(0xFF6573C3)
val colorPrimaryDark = Color(0xFF2C387E)

val colorBackground = Color(0xFFF7F8FA)
val colorOnBackground = Color(0xFFFFFFFF)
val colorSurface = Color(0xFFFFFFFF)
val colorBorder = Color(0xCCEDEDED)

val colorOnPrimary = Color(0xFF212121)
val colorOnSecondary = Color(0xB4212121)
val colorTertiary = Color(0x84212121)


val colorProject1 = Color(0xf7d188)
val colorProject2 = Color(0xf2b085)
val colorProject3 = Color(99,214,250)
val colorProject4 = Color(233,241,164)
val colorProject5 = Color(181,156,250)
val colorProject6 = Color(181,156,242)
val colorProject7 = Color(245,198,167)
val colorProject8 = Color(246,206,209)



/**
 * Container 배경이 필요한 요소
 * Tertiary 분위기 환기 ex) toast, tooltip, primaryContainer가 중복으로 쓰기 애매할때 Accent
 * Variant 대비를 강하게 구분줄때
 * 접두사 On xprtmxm qkrtm duddurdlsemt?
 *
 */

val LightColorScheme = lightColorScheme(

    primary = colorPrimary,
    onPrimary = colorOnPrimary,
    secondary = colorSecondary,
    onSecondary = colorOnSecondary,
    inversePrimary = White,
    onTertiary = colorTertiary,

    background = colorBackground,
    onBackground = colorOnBackground,
    surface = colorSurface,
    onSurface = LightBackground,

    inverseOnSurface = Black,
    outline = colorBorder,
    tertiaryContainer = colorAccent,
    error = colorCaution
    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)
val DarkColorScheme = darkColorScheme(

    primary = colorPrimary,
    onPrimary = colorOnPrimary,
    secondary = colorSecondary,
    onSecondary = colorOnSecondary,
    inversePrimary = White,
    onTertiary = colorTertiary,

    background = colorBackground,
    onBackground = colorOnBackground,
    surface = colorSurface,
    onSurface = LightBackground,

    tertiary = Pink80,
    inverseOnSurface = Black,
    outline = colorBorder,
    tertiaryContainer = colorAccent,

//    val colorBackground = Color(0xFFFFFFFF)
//val colorSurface = Color(0xFFFAFAFA)
//val colorOnPrimary = Color(0xFF1D1B20)
//val colorOnSecondary = Color(0xFF1D1B20)
    error = colorCaution
)
