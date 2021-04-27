package moe.lemonneko.mttk.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import moe.lemonneko.mttk.R
import moe.lemonneko.mttk.data.ViewModel
import moe.lemonneko.mttk.theme.MatatakiTheme
import moe.lemonneko.mttk.utils.logger

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MatatakiTheme {
                SplashView()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed({
            this@SplashActivity.logger.info("Activity state: $state")
            // Should not start Activity after user go to other application.
            if (state == ActivityState.RESUMED) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }, 1500)
    }
}

@Composable
fun SplashView() {
    Column {
        Icon(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "App Icon",
            tint = ViewModel.theme.primary,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
        )
        Box(
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .fillMaxWidth()
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }
}