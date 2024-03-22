package com.gradation.databox

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DataBoxApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}
