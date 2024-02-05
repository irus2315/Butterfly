package kr.twothumb.butterfly

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp
import kr.twothumb.butterfly.ui.theme.ButterflyTheme
import kr.twothumb.lib.logger.DevLog

@HiltAndroidApp
class ButterflyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DevLog.getInstance().apply {
            init("twothumb")
            setMethod(true)
        }
    }
}
