package kr.twothumb.butterfly.common

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SystemUi(
    inverse: Boolean = false
) {
    val systemUiController = rememberSystemUiController()
    if(inverse) {
        if (isSystemInDarkTheme()) {
            systemUiController.setSystemBarsColor(
                color = MaterialTheme.colorScheme.inverseOnSurface,
                darkIcons = true
            )
            systemUiController.setNavigationBarColor(
                color = MaterialTheme.colorScheme.inverseOnSurface,
                darkIcons = true
            )
        } else {
            systemUiController.setSystemBarsColor(
                color = Color(0xFFFF3333),
            )
            systemUiController.setStatusBarColor(color = Color(0xFFFF3333),)
        }
    }
    else {
        if (isSystemInDarkTheme()) {
            systemUiController.setSystemBarsColor(
                color = MaterialTheme.colorScheme.onSurface,
                darkIcons = false
            )
            systemUiController.setNavigationBarColor(
                color = MaterialTheme.colorScheme.onSurface,
                darkIcons = false
            )
        } else {
            systemUiController.setSystemBarsColor(
                color = MaterialTheme.colorScheme.background,
                darkIcons = true
            )
            systemUiController.setNavigationBarColor(
                color = MaterialTheme.colorScheme.background,
                darkIcons = true
            )
        }
    }
}
