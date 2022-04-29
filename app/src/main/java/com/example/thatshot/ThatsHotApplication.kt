package com.example.thatshot

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ThatsHotApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        FlipperProvider.init(this)
    }
}