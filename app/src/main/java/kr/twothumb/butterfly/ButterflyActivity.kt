package kr.twothumb.butterfly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import kr.twothumb.butterfly.navigation.NavGraph
import kr.twothumb.butterfly.ui.theme.ButterflyTheme

@AndroidEntryPoint
class ButterflyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ButterflyTheme {
                NavGraph()
            }
        }
    }
}