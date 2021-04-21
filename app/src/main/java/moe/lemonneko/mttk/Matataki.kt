package moe.lemonneko.mttk

import android.app.Activity
import android.app.Application
import android.os.Bundle
import moe.lemonneko.mttk.activities.ActivityState
import moe.lemonneko.mttk.activities.BaseActivity

class Matataki : Application(), Application.ActivityLifecycleCallbacks {
    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(this)
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        (activity as BaseActivity).state = ActivityState.CREATED
    }

    override fun onActivityStarted(activity: Activity) {
        (activity as BaseActivity).state = ActivityState.STARTED
    }

    override fun onActivityResumed(activity: Activity) {
        (activity as BaseActivity).state = ActivityState.RESUMED
    }

    override fun onActivityPaused(activity: Activity) {
        (activity as BaseActivity).state = ActivityState.PAUSED
    }

    override fun onActivityStopped(activity: Activity) {
        (activity as BaseActivity).state = ActivityState.STOPPED
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

    override fun onActivityDestroyed(activity: Activity) {
        (activity as BaseActivity).state = ActivityState.DESTROYED
    }
}