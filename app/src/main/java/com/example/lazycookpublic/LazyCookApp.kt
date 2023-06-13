package com.example.lazycookpublic

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LazyCookApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
