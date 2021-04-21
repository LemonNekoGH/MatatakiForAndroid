package moe.lemonneko.mttk.activities

import android.content.res.Configuration
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import com.gyf.immersionbar.ktx.immersionBar
import moe.lemonneko.mttk.data.ViewModel
import moe.lemonneko.mttk.theme.matatakiDark
import moe.lemonneko.mttk.utils.logger

abstract class BaseActivity : AppCompatActivity() {
    lateinit var handler: Handler
    var state = ActivityState.NOT_INITIALIZED

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isDarkModeEnabled()) {
            logger.info("Dark mode is enabled.")
            ViewModel.theme = matatakiDark()
        }
        immersionBar {
            transparentStatusBar()
            navigationBarColor("#FFFFFF")
            fitsSystemWindows(true)
            this.statusBarDarkFont(!isDarkModeEnabled())
            this.navigationBarDarkIcon(!isDarkModeEnabled())
        }
        handler = Handler(Looper.getMainLooper(), this::handleMessage)
    }

    protected open fun handleMessage(msg: Message): Boolean {
        return true
    }

    private fun isDarkModeEnabled(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            return resources.configuration.isNightModeActive
        }
        return (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
    }
}