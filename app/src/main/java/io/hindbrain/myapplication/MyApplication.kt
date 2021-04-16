package io.hindbrain.myapplication

import android.app.Application

class MyApplication: Application() {

    companion object{
        var GLOBAL_COUNTER = 0
    }
    override fun onCreate() {
        super.onCreate()

    }

    override fun onTerminate() {
        super.onTerminate()
    }
}