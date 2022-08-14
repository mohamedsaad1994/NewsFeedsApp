package com.link.newsfeedsapp.core.app

import android.app.Application
import com.link.newsfeedsapp.utilities.UtilsFunctions
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NewsFeedsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        UtilsFunctions.setup(applicationContext)
    }
}